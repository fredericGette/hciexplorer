package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.ChannelIdentifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EchoResponse extends SignalingPacket {
    private SignalingCommandCode commandCode = SignalingCommandCode.ECHO_RESPONSE;
}
