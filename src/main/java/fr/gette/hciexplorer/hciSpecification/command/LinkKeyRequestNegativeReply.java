package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LinkKeyRequestNegativeReply extends Command {
    private CommandCode opCode = CommandCode.LINK_KEY_REQUEST_NEGATIVE_REPLY;
    private BluetoothAddress bdAddr;
}
