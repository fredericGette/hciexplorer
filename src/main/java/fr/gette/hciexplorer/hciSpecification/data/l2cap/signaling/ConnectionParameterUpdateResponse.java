package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionParameterUpdateResponse extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.CONNECTION_PARAMETER_UPDATE_RESPONSE;
    private int result;

    public String getResultDescription()
    {
        String description;
        switch(result)
        {
            case 0x0000 -> description = "Connection Parameters accepted";
            case 0x0001 -> description = "Connection Parameters rejected";
            default -> description = "Reserved";
        }
        return description;
    }

}
