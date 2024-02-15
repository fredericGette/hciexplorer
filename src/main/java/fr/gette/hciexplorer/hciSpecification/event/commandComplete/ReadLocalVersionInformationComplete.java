package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * LMP	Bluetooth Version
 * 0	 Bluetooth 1.0b
 * 1	 Bluetooth 1.1
 * 2	 Bluetooth 1.2
 * 3	 Bluetooth 2.0 + EDR
 * 4	 Bluetooth 2.1 + EDR
 * 5	 Bluetooth 3.0 + HS
 * 6	 Bluetooth 4.0 + LE (Bluetooth Smart)
 * 7	 Bluetooth 4.1
 * 8	 Bluetooth 4.2
 * 9	 Bluetooth 5
 */
public class ReadLocalVersionInformationComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LOCAL_VERSION_INFORMATION;
    private ErrorCode status;
    private short hciVersion;
    private int hciSubversion;
    private short lmpVersion;
    private int companyIdentifier;
    private int lmpSubversion;
}
