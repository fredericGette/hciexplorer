package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.hciSpecification.EightByteValue;
import fr.gette.hciexplorer.hciSpecification.SixteenByteValue;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.Frame;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.SimpleDataFrame;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute.*;
import fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager.*;
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
            case ECHO_RESPONSE -> packet = buildEchoResponse(data);
            case CONNECTION_PARAMETER_UPDATE_REQUEST -> packet = buildConnectionParameterUpdateRequest(data);
            case CONNECTION_PARAMETER_UPDATE_RESPONSE -> packet = buildConnectionParameterUpdateResponse(data);
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
            case PAIRING_CONFIRM -> packet = buildPairingConfirm(data);
            case PAIRING_RANDOM -> packet = buildPairingRandom(data);
            case PAIRING_FAILED -> packet = buildPairingFailed(data);
            case ENCRYPTION_INFORMATION -> packet = buildEncryptionInformation(data);
            case MASTER_IDENTIFICATION -> packet = buildMasterIdentification(data);
            case SIGNING_INFORMATION -> packet = buildSigningInformation(data);
            default -> throw new UnsupportedOperationException(
                    String.format("Code : %s", commandCode));
        }

        return packet;
    }

    private AttributePacket buildAttributePacket(BinaryMessage data) {
        AttributePacket packet;

        AttributeOpcode opcode = AttributeOpcode.get(data.read1octet());

        switch (opcode){
            case ERROR_RESPONSE -> packet = buildErrorResponse(data);
            case EXCHANGE_MTU_REQUEST -> packet = buildExchangeMTURequest(data);
            case EXCHANGE_MTU_RESPONSE -> packet = buildExchangeMTUResponse(data);
            case FIND_BY_TYPE_VALUE_REQUEST -> packet = buildFindByTypeValueRequest(data);
            case READ_BY_TYPE_REQUEST -> packet = buildReadByTypeRequest(data);
            case FIND_BY_TYPE_VALUE_RESPONSE -> packet = buildFindByTypeValueResponse(data);
            case READ_BY_GROUP_TYPE_REQUEST -> packet = buildReadByGroupTypeRequest(data);
            case READ_BY_GROUP_TYPE_RESPONSE -> packet = buildReadByGroupTypeResponse(data);
            case READ_BY_TYPE_RESPONSE -> packet = buildReadByTypeResponse(data);
            case FIND_INFORMATION_REQUEST -> packet = buildFindInformationRequest(data);
            case READ_REQUEST -> packet = buildReadRequest(data);
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

    private EchoResponse buildEchoResponse(BinaryMessage data){
        EchoResponse command = new EchoResponse();
        return command;
    }

    private ConnectionParameterUpdateRequest buildConnectionParameterUpdateRequest(BinaryMessage data){
        ConnectionParameterUpdateRequest command = new ConnectionParameterUpdateRequest();
        command.setIntervalMin(data.read2octets());
        command.setIntervalMax(data.read2octets());
        command.setSlaveLatency(data.read2octets());
        command.setTimeoutMultiplier(data.read2octets());
        return command;
    }

    private ConnectionParameterUpdateResponse buildConnectionParameterUpdateResponse(BinaryMessage data){
        ConnectionParameterUpdateResponse command = new ConnectionParameterUpdateResponse();
        command.setResult(data.read2octets());
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

    private PairingConfirm buildPairingConfirm(BinaryMessage data){
        PairingConfirm packet = new PairingConfirm();
        packet.setConfirmValue(new SixteenByteValue(data));
        return packet;
    }

    private PairingRandom buildPairingRandom(BinaryMessage data){
        PairingRandom packet = new PairingRandom();
        packet.setRandomValue(new SixteenByteValue(data));
        return packet;
    }

    private PairingFailed buildPairingFailed(BinaryMessage data) {
        PairingFailed packet = new PairingFailed();
        packet.setReason(data.read1octet());
        return packet;
    }

    private EncryptionInformation buildEncryptionInformation(BinaryMessage data) {
        EncryptionInformation packet = new EncryptionInformation();
        packet.setLongTermKey(new SixteenByteValue(data));
        return packet;
    }

    private MasterIdentification buildMasterIdentification(BinaryMessage data) {
        MasterIdentification packet = new MasterIdentification();
        packet.setEdiv(data.read2octets());
        packet.setRand(new EightByteValue(data));
        return packet;
    }

    private SigningInformation buildSigningInformation(BinaryMessage data) {
        SigningInformation packet = new SigningInformation();
        packet.setSignatureKey(new SixteenByteValue(data));
        return packet;
    }

    private ErrorResponse buildErrorResponse(BinaryMessage data){
        ErrorResponse packet = new ErrorResponse();
        packet.setRequestOpcodeInError(AttributeOpcode.get(data.read1octet()));
        packet.setAttributeHandleInError(data.read2octets());
        packet.setErrorCode(data.read1octet());
        return packet;
    }

    private ExchangeMTURequest buildExchangeMTURequest(BinaryMessage data){
        ExchangeMTURequest packet = new ExchangeMTURequest();
        packet.setClientRxMTU(data.read2octets());
        return packet;
    }

    private ExchangeMTUResponse buildExchangeMTUResponse(BinaryMessage data){
        ExchangeMTUResponse packet = new ExchangeMTUResponse();
        packet.setServerRxMTU(data.read2octets());
        return packet;
    }

    private FindByTypeValueRequest buildFindByTypeValueRequest(BinaryMessage data){
        FindByTypeValueRequest packet = new FindByTypeValueRequest();
        packet.setStartingHandle(data.read2octets());
        packet.setEndingHandle(data.read2octets());
        packet.setAttributeType(new AttributeType(data.read2octets()));
        packet.setAttributeValue(data);
        return packet;
    }

    private ReadByTypeRequest buildReadByTypeRequest(BinaryMessage data){
        ReadByTypeRequest packet = new ReadByTypeRequest();
        packet.setStartingHandle(data.read2octets());
        packet.setEndingHandle(data.read2octets());
        packet.setAttributeType(new AttributeType(data));
        return packet;
    }

    private FindByTypeValueResponse buildFindByTypeValueResponse(BinaryMessage data){
        FindByTypeValueResponse packet = new FindByTypeValueResponse();
        packet.setFoundAttributeHandle(data.read2octets());
        packet.setGroupEndHandle(data.read2octets());
        return packet;
    }

    private ReadByGroupTypeRequest buildReadByGroupTypeRequest(BinaryMessage data){
        ReadByGroupTypeRequest packet = new ReadByGroupTypeRequest();
        packet.setStartingHandle(data.read2octets());
        packet.setEndingHandle(data.read2octets());
        packet.setAttributeGroupType(new AttributeGroupType(data));
        return packet;
    }

    private ReadByGroupTypeResponse buildReadByGroupTypeResponse(BinaryMessage data){
        ReadByGroupTypeResponse packet = new ReadByGroupTypeResponse();
        packet.setAttributeDataList(new AttributeDataList(data));
        return packet;
    }

    private ReadByTypeResponse buildReadByTypeResponse(BinaryMessage data){
        ReadByTypeResponse packet = new ReadByTypeResponse();
        packet.setAttributeDataList(new AttributeDataList(data));
        return packet;
    }

    private FindInformationRequest buildFindInformationRequest(BinaryMessage data){
        FindInformationRequest packet = new FindInformationRequest();
        packet.setStartingHandle(data.read2octets());
        packet.setEndingHandle(data.read2octets());
        return packet;
    }

    private ReadRequest buildReadRequest(BinaryMessage data){
        ReadRequest packet = new ReadRequest();
        packet.setHandle(data.read2octets());
        return packet;
    }
}
