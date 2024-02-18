package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.*;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.data.Data;
import fr.gette.hciexplorer.hciSpecification.data.DataCanceled;
import fr.gette.hciexplorer.hciSpecification.data.DataUnfinished;
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
        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decode(endData, ioCtlStatus);
    }

    HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end)
    {
        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decode(endData, ioCtlStatus);
    }

    HciMessage decode(IoCtlMessage endData, IoCtlStatus ioCtlStatus)
    {
        HciMessage hciMsg;

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.read1octet());

                Data data = new Data();

                hciMsg = data;
            }
            case STATUS_CANCELLED -> {
                DataCanceled dataCanceled = new DataCanceled();
                dataCanceled.setIoCtlStatus(ioCtlStatus);
                hciMsg = dataCanceled;
            }
            case STATUS_UNFINISHED -> {
                DataUnfinished dataUnfinished = new DataUnfinished();
                dataUnfinished.setIoCtlStatus(ioCtlStatus);
                hciMsg = dataUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
    }
}
