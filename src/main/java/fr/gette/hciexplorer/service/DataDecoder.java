package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.*;
import fr.gette.hciexplorer.hciSpecification.*;
import fr.gette.hciexplorer.hciSpecification.data.AclData;
import fr.gette.hciexplorer.hciSpecification.data.AclDataCanceled;
import fr.gette.hciexplorer.hciSpecification.data.AclDataUnfinished;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
class DataDecoder {

    HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        HciMessage hciMsg;

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.read1octet());

                hciMsg = decode(endData, AclDirection.CONTROLLER_TO_HOST);
            }
            case STATUS_CANCELLED -> {
                AclDataCanceled dataCanceled = new AclDataCanceled();
                dataCanceled.setIoCtlStatus(ioCtlStatus);
                dataCanceled.setDirection(AclDirection.CONTROLLER_TO_HOST);
                hciMsg = dataCanceled;
            }
            case STATUS_UNFINISHED -> {
                AclDataUnfinished dataUnfinished = new AclDataUnfinished();
                dataUnfinished.setIoCtlStatus(ioCtlStatus);
                dataUnfinished.setDirection(AclDirection.CONTROLLER_TO_HOST);
                hciMsg = dataUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
    }

    HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end)
    {
        throw new UnsupportedOperationException();
    }

    private AclData decode(IoCtlMessage data, AclDirection direction)
    {
        AclData aclData = new AclData();
        aclData.setDirection(direction);

        BigInteger header = BigInteger.valueOf(data.read4octets());
        BigInteger handle = header.and(BigInteger.valueOf(0xFFF));
        BigInteger pbFlag = header.shiftRight(12).and(BigInteger.valueOf(0x3));
        BigInteger bcFlag = header.shiftRight(14).and(BigInteger.valueOf(0x3));
        BigInteger dataTotalLength = header.shiftRight(16);

        aclData.setHandle(handle.intValue());
        aclData.setPacketBoundaryFlag(pbFlag.shortValue());
        aclData.setBroadcastFlag(bcFlag.shortValue());
        aclData.setDataTotalLength(dataTotalLength.intValue());
        aclData.setData(data.readRemaining());

        return aclData;
    }

}
