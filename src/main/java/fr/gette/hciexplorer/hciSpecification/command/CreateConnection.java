package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateConnection extends Command {
    private CommandCode opCode = CommandCode.CREATE_CONNECTION;
    private BluetoothAddress bdAddr;
    private PacketTypeUsable packetType;
    private PageScanRepetitionMode pageScanRepetitionMode;
    private short reserved; // must be set to 0x00
    private ClockOffset clockOffset;
    private AllowRoleSwitch allowRoleSwitch;
}
