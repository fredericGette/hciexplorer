package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadAdvertisingPhysicalChannelTxPower extends Command {
    private CommandCode opCode = CommandCode.LE_READ_ADVERTISING_PHYSICAL_CHANNEL_TX_POWER;
}
