package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.ClockOffset;
import fr.gette.hciexplorer.hciSpecification.PageScanRepetitionMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoteNameRequest extends Command {
    private CommandCode opCode = CommandCode.REMOTE_NAME_REQUEST;
    private BluetoothAddress bdAddr;
    private PageScanRepetitionMode pageScanRepetitionMode;
    private short           reserved; // must be set to 0x00
    private ClockOffset     clockOffset;
}
