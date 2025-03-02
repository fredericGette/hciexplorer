package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class CommandReject extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.COMMAND_REJECT;
    private int reason;

    public abstract String getReasonDescription();
}
