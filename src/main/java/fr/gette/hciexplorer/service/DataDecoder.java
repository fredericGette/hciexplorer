package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.*;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class DataDecoder {

    HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = endData.readULong(); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.readUChar());

            Data data = new Data();

            hciMsg = data;
        }
        else
        {
            DataFailed dataFailed = new DataFailed();
            dataFailed.setIoCtlStatus(IoCtlStatus.get(end.getStatus()));
            hciMsg = dataFailed;
        }

        return hciMsg;
    }

    HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());

        if (EndRawMessage.STATUS_SUCCESS.equals(end.getStatus()))
        {
            long size = endData.readULong(); // size of the HCI packet
            HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.readUChar());

            Data data = new Data();

            hciMsg = data;
        }
        else
        {
            DataFailed dataFailed = new DataFailed();
            dataFailed.setIoCtlStatus(IoCtlStatus.get(end.getStatus()));
            hciMsg = dataFailed;
        }

        return hciMsg;
    }
}
