package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static fr.gette.hciexplorer.service.DecoderHelper.readUChar;
import static fr.gette.hciexplorer.service.DecoderHelper.readULong;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataDecoder {

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        DecoderHelper.IoMessage endData = new DecoderHelper.IoMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = readULong(endData); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(readUChar(endData));

            Data data = new Data();

            hciMsg = data;
        }
        else
        {
            DataFailed dataFailed = new DataFailed();
            dataFailed.setErrorCode(end.getStatus());
            hciMsg = dataFailed;
        }

        return hciMsg;
    }
}
