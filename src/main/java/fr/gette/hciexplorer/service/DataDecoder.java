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
public class DataDecoder {

    public HciMessage decodeRead(String data)
    {
        IoCtlMessage endData = new IoCtlMessage(data);

        return decodeRead(endData, IoCtlStatus.STATUS_SUCCESS);
    }

    public HciMessage decodeWrite(String data)
    {
        IoCtlMessage beginData = new IoCtlMessage(data);

        return decodeWrite(beginData, IoCtlStatus.STATUS_SUCCESS);
    }

    public HciMessage decode(BeginReadRawMessage begin, EndReadRawMessage end) {
        HciMessage hciMsg;

        IoCtlMessage endData = new IoCtlMessage(end.getOutputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decodeRead(endData, ioCtlStatus);
    }

    private HciMessage decodeRead(IoCtlMessage endData, IoCtlStatus ioCtlStatus) {
        HciMessage hciMsg;

        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = endData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeEnd = HciPacketType.get(endData.read1octet());
                if (!HciPacketType.ACLDATA.equals(hciPacketTypeEnd))
                {
                    throw new IllegalArgumentException(String.format(
                            "Expected packet type 'ACLDATA' (0x02), found %s",hciPacketTypeEnd));
                }

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

    HciMessage decode(BeginWriteRawMessage begin, EndWriteRawMessage end) {
        HciMessage hciMsg;

        IoCtlMessage beginData = new IoCtlMessage(begin.getInputBuffer());
        IoCtlStatus ioCtlStatus = IoCtlStatus.get(end.getStatus());

        return decodeWrite(beginData, ioCtlStatus);
    }

    private HciMessage decodeWrite(IoCtlMessage beginData, IoCtlStatus ioCtlStatus) {
        HciMessage hciMsg;
        switch (ioCtlStatus)
        {
            case STATUS_SUCCESS -> {
                long size = beginData.read4octets(); // size of the HCI packet
                HciPacketType hciPacketTypeBegin = HciPacketType.get(beginData.read1octet());
                if (!HciPacketType.ACLDATA.equals(hciPacketTypeBegin))
                {
                    throw new IllegalArgumentException(String.format(
                            "Expected packet type 'ACLDATA' (0x02), found %s",hciPacketTypeBegin));
                }

                hciMsg = decode(beginData, AclDirection.HOST_TO_CONTROLLER);
            }
            case STATUS_CANCELLED -> {
                AclDataCanceled dataCanceled = new AclDataCanceled();
                dataCanceled.setIoCtlStatus(ioCtlStatus);
                dataCanceled.setDirection(AclDirection.HOST_TO_CONTROLLER);
                hciMsg = dataCanceled;
            }
            case STATUS_UNFINISHED -> {
                AclDataUnfinished dataUnfinished = new AclDataUnfinished();
                dataUnfinished.setIoCtlStatus(ioCtlStatus);
                dataUnfinished.setDirection(AclDirection.HOST_TO_CONTROLLER);
                hciMsg = dataUnfinished;
            }
            default -> throw new UnsupportedOperationException(
                    String.format("Status : %s",ioCtlStatus));
        }

        return hciMsg;
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
