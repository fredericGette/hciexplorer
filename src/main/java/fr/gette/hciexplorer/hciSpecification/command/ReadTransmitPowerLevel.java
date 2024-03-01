package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.TransmitPowerLevelType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReadTransmitPowerLevel extends Command {
    private CommandCode opCode = CommandCode.READ_TRANSMIT_POWER_LEVEL;
    private int                    connectionHandle;
    private TransmitPowerLevelType transmitPowerLevelType;
}
