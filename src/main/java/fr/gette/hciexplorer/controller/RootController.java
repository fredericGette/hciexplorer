package fr.gette.hciexplorer.controller;

import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.command.Command;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.service.MessageService;
import fr.gette.hciexplorer.service.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RootController
{
	private final Parser parser;
	private final MessageService messageService;

	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
		log.info("Uploaded file [{}] {} bytes", file.getOriginalFilename(), file.getSize());
		parser.parse(file.getInputStream());
	}

	@GetMapping("/readMessage/{id}")
	@ResponseBody
	public HciMessage getReadMessage(@PathVariable Long id)
	{
		HciMessage readMessage = messageService.getReadMessage(id);
		return readMessage;
	}

	@GetMapping("/writeMessage/{id}")
	@ResponseBody
	public HciMessage getWriteMessage(@PathVariable Long id)
	{
		HciMessage readMessage = messageService.getWriteMessage(id);
		return readMessage;
	}

	@CrossOrigin
	@GetMapping("/messages")
	@ResponseBody
	public List<HciMessage> getMessages()
	{
		List<HciMessage> messages = messageService.getMessages();
		return messages;
	}

	@CrossOrigin
	@GetMapping("/eventMessages")
	@ResponseBody
	public List<Event> getEventMessages()
	{
		List<Event> events = messageService.getEventMessages();
		return events;
	}

	@CrossOrigin
	@GetMapping("/commandMessages")
	@ResponseBody
	public List<Command> getCommandMessages()
	{
		List<Command> commands = messageService.getCommandMessages();
		return commands;
	}

}
