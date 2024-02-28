package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeReadConnectListSize extends Command {
    private CommandCode opCode = CommandCode.LE_READ_CONNECT_LIST_SIZE;
}
