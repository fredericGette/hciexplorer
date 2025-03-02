package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.Frame;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.SimpleDataFrame;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute.AttributeOpcode;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute.AttributePacket;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute.ExchangeMTURequest;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager.PairingRequest;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager.PairingResponse;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager.SecurityManagerCommandCode;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager.SecurityManagerPacket;
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
        int cid = data.read2octets();

        switch (cid){
            case 0x0001 -> packet = buildSignalingPacket(data);
            case 0x0004 -> packet = buildAttributePacket(data);
            case 0x0005 -> packet = buildSignalingPacket(data);
            case 0x0006 -> packet = buildSecurityManagerProtocolPacket(data);
            default -> packet = buildSimpleDataFrame(data);
        }
        packet.setCID(cid);

        return packet;
    }

    private SignalingPacket buildSignalingPacket(BinaryMessage data){
        SignalingPacket packet;

        SignalingCommandCode commandCode = SignalingCommandCode.get(data.read1octet());
        short identifier = data.read1octet();
        int length = data.read2octets();

        switch (commandCode){
            case COMMAND_REJECT -> packet = buildCommandReject(data);
            case CONNECTION_REQUEST -> packet = buildConnectionRequest(data);
            case CONNECTION_RESPONSE -> packet = buildConnectionResponse(data);
            case CONFIGURATION_REQUEST -> packet = buildConfigurationRequest(data, length);
            case CONFIGURATION_RESPONSE -> packet = buildConfigurationResponse(data, length);
            case DISCONNECTION_REQUEST -> packet = buildDisconnectionRequest(data);
            case DISCONNECTION_RESPONSE -> packet = buildDisconnectionResponse(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Code : %s", commandCode));
        }
        packet.setIdentifier(identifier);

        return packet;
    }

    private SecurityManagerPacket buildSecurityManagerProtocolPacket(BinaryMessage data) {
        SecurityManagerPacket packet;

        SecurityManagerCommandCode commandCode = SecurityManagerCommandCode.get(data.read1octet());

        switch (commandCode){
            case PAIRING_REQUEST -> packet = buildPairingRequest(data);
            case PAIRING_RESPONSE -> packet = buildPairingResponse(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Code : %s", commandCode));
        }

        return packet;
    }

    private AttributePacket buildAttributePacket(BinaryMessage data) {
        AttributePacket packet;

        AttributeOpcode opcode = AttributeOpcode.get(data.read1octet());

        switch (opcode){
            case EXCHANGE_MTU_REQUEST -> packet = buildExchangeMTURequest(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Code : %s", opcode));
        }
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

    private ConnectionRequest buildConnectionRequest(BinaryMessage data){
        ConnectionRequest command = new ConnectionRequest();
        command.setPsm(data.read2octets());
        command.setSourceCID(data.read2octets());
        return command;
    }

    private ConnectionResponse buildConnectionResponse(BinaryMessage data){
        ConnectionResponse command = new ConnectionResponse();
        command.setDestinationCID(data.read2octets());
        command.setSourceCID(data.read2octets());
        command.setResult(data.read2octets());
        command.setStatus(data.read2octets());
        return command;
    }

    private ConfigurationRequest buildConfigurationRequest(BinaryMessage data, int length){
        ConfigurationRequest command = new ConfigurationRequest(data, length);
        return command;
    }

    private ConfigurationResponse buildConfigurationResponse(BinaryMessage data, int length){
        ConfigurationResponse command = new ConfigurationResponse(data, length);
        return command;
    }

    private DisconnectionRequest buildDisconnectionRequest(BinaryMessage data){
        DisconnectionRequest command = new DisconnectionRequest();
        command.setDestinationCID(data.read2octets());
        command.setSourceCID(data.read2octets());
        return command;
    }

    private DisconnectionResponse buildDisconnectionResponse(BinaryMessage data){
        DisconnectionResponse command = new DisconnectionResponse();
        command.setDestinationCID(data.read2octets());
        command.setSourceCID(data.read2octets());
        return command;
    }

    private SimpleDataFrame buildSimpleDataFrame(BinaryMessage data){
        SimpleDataFrame packet = new SimpleDataFrame();
        packet.setData(data.readRemaining());
        return packet;
    }

    private PairingRequest buildPairingRequest(BinaryMessage data){
        PairingRequest packet = new PairingRequest();
        packet.setIOCapability(data.read1octet());
        packet.setOOBDataFlag(data.read1octet());
        packet.setAuthReq(data.read1octet());
        packet.setMaximumEncryptionKeySize(data.read1octet());
        packet.setInitiatorKeyDistribution(data.read1octet());
        packet.setResponderKeyDistribution(data.read1octet());
        return packet;
    }

    private PairingResponse buildPairingResponse(BinaryMessage data){
        PairingResponse packet = new PairingResponse();
        packet.setIOCapability(data.read1octet());
        packet.setOOBDataFlag(data.read1octet());
        packet.setAuthReq(data.read1octet());
        packet.setMaximumEncryptionKeySize(data.read1octet());
        packet.setInitiatorKeyDistribution(data.read1octet());
        packet.setResponderKeyDistribution(data.read1octet());
        return packet;
    }

    private ExchangeMTURequest buildExchangeMTURequest(BinaryMessage data){
        ExchangeMTURequest packet = new ExchangeMTURequest();
        packet.setClientRxMTU(data.read2octets());
        return packet;
    }
}
