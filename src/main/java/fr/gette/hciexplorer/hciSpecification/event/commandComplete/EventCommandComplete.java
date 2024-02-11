package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import fr.gette.hciexplorer.hciSpecification.event.EventCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class EventCommandComplete extends Event {
    private EventCode code = EventCode.COMMAND_COMPLETE;

    /*
     Allows the Controller to indicate the number of HCI command packets the Host can send to the Controller.
        If the Controller requires the Host to stop sending commands, the
        Num_HCI_Command_Packets event parameter will be set to zero. To indicate
        to the Host that the Controller is ready to receive HCI command packets, the
        Controller generates an HCI_Command_Complete event with the
        Command_Opcode set to 0x0000 and the Num_HCI_Command_Packets
        event parameter set to 1 or more. Command_Opcode 0x0000 is a special
        value indicating that this event is not associated with a command sent by the
        Host. The Controller can send an HCI_Command_Complete event with
        Command Opcode 0x0000 at any time to change the number of outstanding
        HCI command packets that the Host can send before waiting.
     */
    private short numHciCommandPackets;
}
