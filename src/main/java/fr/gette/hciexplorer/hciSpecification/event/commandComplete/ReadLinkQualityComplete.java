package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadLinkQualityComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LINK_QUALITY;
    private ErrorCode status;
    private int handle;
    private short linkQuality;
}
