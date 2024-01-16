package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginRawMessage;
import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import fr.gette.hciexplorer.entity.DataRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import fr.gette.hciexplorer.entity.RawMessage;
import fr.gette.hciexplorer.entity.UnparsedRawMessage;
import fr.gette.hciexplorer.repository.BeginReadRawMessageRepository;
import fr.gette.hciexplorer.repository.BeginWriteRawMessageRepository;
import fr.gette.hciexplorer.repository.EndReadRawMessageRepository;
import fr.gette.hciexplorer.repository.EndWriteRawMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class Parser
{
	private static final String IOCTL_BTHX_READ_HCI = "0x410413";
	private static final String IOCTL_BTHX_WRITE_HCI = "0x41040F";
	private final Pattern rawLinePattern = Pattern.compile("^((?:[^!]*!){3})([^!]*)"); // The delimiter is the third "!"
	private final Pattern rawLineHeaderPattern = Pattern.compile("^([^ ]* [^ ]*) ([^!]*)!([^!]*)!([^!]*)!"); // MM-dd HH:mm:ss.SSS HCI!lowerDeviceObject-lowerDriverName!uniqueID!
	private final Pattern inputPattern = Pattern.compile("^Receive IoControlCode=([^ ]*) InputBufferLength=([^ ]*) OutputBufferLength=([^ ]*)");
	private final Pattern ouputPattern = Pattern.compile("^Complete IoControlCode=([^ ]*) OutputBufferLength=([^ ]*) Status=0x([^ ]*)");
	private final Pattern dataPattern  = Pattern.compile("^([0-9A-F]{2} )*");
	//private final DateTimeFormatter timestampHeaderFormatter  = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss.SSS");
	private final  DateTimeFormatter timestampHeaderFormatter = new DateTimeFormatterBuilder()
			.appendPattern("MM-dd HH:mm:ss.SSS")
			.parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
			.toFormatter();

	private final BeginReadRawMessageRepository  beginReadRawMessageRepository;
	private final BeginWriteRawMessageRepository beginWriteRawMessageRepository;
	private final EndReadRawMessageRepository  endReadRawMessageRepository;
	private final EndWriteRawMessageRepository endWriteRawMessageRepository;

	public void parse(InputStream inputStream) throws IOException
	{
		Map<Long, RawMessage> rawMessages = new HashMap<>();

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)))
		{
			String line = null;
			while ((line = reader.readLine()) != null) {
				try
				{
					parseRawLine(line, rawMessages);
				}
				catch (Exception e)
				{
					log.info("Discard-{} [{}]", e.getMessage(), line);
				}
			}
		}
	}

	private void parseRawLine(String line, Map<Long, RawMessage> rawMessages) throws ParseException
	{
		Matcher rawLineMatcher = rawLinePattern.matcher(line);
		if (!rawLineMatcher.matches())
		{
			// Not a line having a delimiter
			throw new ParseException("Wrong delimiter", 0);
		}
		Long rawMsgId = parseRawLineHeader(rawLineMatcher.group(1), rawMessages);
		parseRawLineValue(rawLineMatcher.group(2), rawMsgId, rawMessages);
	}

	private Long parseRawLineHeader(String header, Map<Long, RawMessage> rawMessages) throws ParseException
	{
		Matcher rawLineHeaderMatcher = rawLineHeaderPattern.matcher(header);
		if (!rawLineHeaderMatcher.matches())
		{
			throw new ParseException("Wrong header", 0);
		}

		if (!"HCI".equals(rawLineHeaderMatcher.group(2)))
		{
			throw new ParseException("Wrong filter ID", 0);
		}

		LocalDateTime ldt = LocalDateTime.parse(rawLineHeaderMatcher.group(1), timestampHeaderFormatter);
		ZonedDateTime timestamp = ZonedDateTime.of(ldt, ZoneId.systemDefault());

		Long rawMsgId = Long.parseLong(rawLineHeaderMatcher.group(4), 16);

		rawMessages.computeIfAbsent(rawMsgId, id -> {
			UnparsedRawMessage rawMsg = new UnparsedRawMessage();
			rawMsg.setId(id);
			rawMsg.setTimestamp(timestamp);
			return rawMsg;
		});

		return rawMsgId;
	}

	private void parseRawLineValue(String value, Long rawMsgId, Map<Long, RawMessage> rawMessages) throws ParseException
	{
		Matcher inputMatcher = inputPattern.matcher(value);
		Matcher ouputMatcher = ouputPattern.matcher(value);
		Matcher dataMatcher = dataPattern.matcher(value);
		if (inputMatcher.matches())
		{
			UnparsedRawMessage rawMsg = (UnparsedRawMessage) rawMessages.get(rawMsgId);
			BeginRawMessage beginRawMsg = buildBeginRawMessage(rawMsg, inputMatcher);
			rawMessages.replace(rawMsgId, beginRawMsg);
		}
		else if (ouputMatcher.matches())
		{
			UnparsedRawMessage rawMsg = (UnparsedRawMessage)rawMessages.get(rawMsgId);
			EndRawMessage endRawMsg = buildEndRawMessage(rawMsg, ouputMatcher);
			rawMessages.replace(rawMsgId, endRawMsg);
		}
		else if (dataMatcher.find())
		{
			DataRawMessage dataRawMsg = (DataRawMessage)rawMessages.get(rawMsgId);
			dataRawMsg.addData(dataMatcher.group(0));

			if (dataRawMsg.parsingFinished())
			{
				log.info("Parsing finished {}", dataRawMsg);
				persist(dataRawMsg);
				rawMessages.remove(rawMsgId);
			}
		}
		else
		{
			throw new ParseException("Unknown value",0);
		}
	}

	private void persist(DataRawMessage dataRawMsg)
	{
		if (dataRawMsg instanceof BeginReadRawMessage)
		{
			this.beginReadRawMessageRepository.save((BeginReadRawMessage)dataRawMsg);
		}
		else if (dataRawMsg instanceof BeginWriteRawMessage)
		{
			this.beginWriteRawMessageRepository.save((BeginWriteRawMessage)dataRawMsg);
		}
		else if (dataRawMsg instanceof EndReadRawMessage)
		{
			this.endReadRawMessageRepository.save((EndReadRawMessage)dataRawMsg);
		}
		else if (dataRawMsg instanceof EndWriteRawMessage)
		{
			this.endWriteRawMessageRepository.save((EndWriteRawMessage)dataRawMsg);
		}
	}

	private EndRawMessage buildEndRawMessage(UnparsedRawMessage rawMsg, Matcher ouputMatcher) throws ParseException
	{
		EndRawMessage endRawMsg = null;

		String IoControlCode = ouputMatcher.group(1);
		if (IOCTL_BTHX_READ_HCI.equals(IoControlCode))
		{
			endRawMsg = new EndReadRawMessage(rawMsg);
		}
		else if (IOCTL_BTHX_WRITE_HCI.equals(IoControlCode))
		{
			endRawMsg = new EndWriteRawMessage(rawMsg);
		}
		else
		{
			throw new ParseException("Unknown completed IoControlCode",0);
		}

		Integer outputBufferLength = Integer.parseInt(ouputMatcher.group(2));
		endRawMsg.setOutputBuffer(new byte[outputBufferLength]);

		Long status = Long.parseLong(ouputMatcher.group(3), 16);
		endRawMsg.setStatus(status);
		return endRawMsg;
	}

	private BeginRawMessage buildBeginRawMessage(UnparsedRawMessage rawMsg, Matcher inputMatcher) throws ParseException
	{
		BeginRawMessage beginRawMsg = null;

		String IoControlCode = inputMatcher.group(1);
		if (IOCTL_BTHX_READ_HCI.equals(IoControlCode))
		{
			beginRawMsg = new BeginReadRawMessage(rawMsg);
		}
		else if (IOCTL_BTHX_WRITE_HCI.equals(IoControlCode))
		{
			beginRawMsg = new BeginWriteRawMessage(rawMsg);
		}
		else
		{
			throw new ParseException("Unknown received IoControlCode",0);
		}

		Integer inputBufferLength = Integer.parseInt(inputMatcher.group(2));
		beginRawMsg.setInputBuffer(new byte[inputBufferLength]);

		return beginRawMsg;
	}

}
