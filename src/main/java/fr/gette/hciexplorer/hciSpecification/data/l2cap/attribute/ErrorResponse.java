package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends AttributePacket {
    AttributeOpcode opcode = AttributeOpcode.ERROR_RESPONSE;
    private AttributeOpcode requestOpcodeInError;
    private int attributeHandleInError;
    private short errorCode;

    public String getErrorCode(){
        String description;
        switch (errorCode)
        {
            case 0x01 -> description="Invalid Handle";
            case 0x02 -> description="Read Not Permitted";
            case 0x03 -> description="Write Not Permitted";
            case 0x04 -> description="Invalid PDU";
            case 0x05 -> description="Insufficient Authentication";
            case 0x06 -> description="Request Not Supported";
            case 0x07 -> description="Invalid Offset";
            case 0x08 -> description="Insufficient Authorization";
            case 0x09 -> description="Prepare Queue Full";
            case 0x0A -> description="Attribute Not Found";
            case 0x0B -> description="Attribute Not Long";
            case 0x0C -> description="Insufficient Encryption Key Size";
            case 0x0D -> description="Invalid Attribute Value Length";
            case 0x0E -> description="Unlikely Error";
            case 0x0F -> description="Insufficient Encryption";
            case 0x10 -> description="Unsupported Group Type";
            case 0x11 -> description="Insufficient Resources";
            default -> description = "Unknown";
        }
        return String.format("%s (0x%02X)", description, errorCode);
    }
}
