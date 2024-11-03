package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.Frame;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling.*;
import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class L2capDecoder {


    public Frame decode(BinaryMessage data) {
        Frame packet;

        int length = data.read2octets();
        int CID = data.read2octets();

        switch (CID){
            case 0x0001 -> packet = buildSignalingPacket(data);
            case 0x0005 -> packet = buildSignalingPacket(data);
            default -> throw new UnsupportedOperationException(
                    String.format("CID : 0x%04X",CID));
        }

        return packet;
    }

    private SignalingPacket buildSignalingPacket(BinaryMessage data){
        SignalingPacket packet;

        CommandCode commandCode = CommandCode.get(data.read1octet());
        short identifier = data.read1octet();
        int length = data.read2octets();

        switch (commandCode){
            case COMMAND_REJECT -> packet = buildCommandReject(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Code : %s", commandCode));
        }
        packet.setIdentifier(identifier);

        return packet;
    }

    private CommandReject buildCommandReject(BinaryMessage data){
        CommandReject command;

        int reason = data.read2octets();
        switch(reason){
            case 0x0000 -> command = new CommandNotUnderstood();
            case 0x0001 -> command = buildMTUExceeded(data);
            case 0x0002 -> command = buildInvalidCIDInRequest(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Reason : 0x%04X", reason));
        }
        command.setReason(reason);

        return command;
    }

    private MTUExceeded buildMTUExceeded(BinaryMessage data){
        MTUExceeded command = new MTUExceeded();
        command.setMtu(data.read2octets());
        return command;
    }

    private InvalidCIDInRequest buildInvalidCIDInRequest(BinaryMessage data){
        InvalidCIDInRequest command = new InvalidCIDInRequest();
        command.setLocalCID(data.read2octets());
        command.setRemoteCID(data.read2octets());
        return command;
    }
}
