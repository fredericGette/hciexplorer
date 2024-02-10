package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommandFailed extends HciMessage {
    private HciPacketType type = HciPacketType.Command;
    private long errorCode;
}
