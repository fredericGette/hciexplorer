package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventCommandStatus extends Event {
    private EventCode code = EventCode.COMMAND_STATUS;
    private ErrorCode status;
    /*
     Allows the Controller to indicate the number of HCI command packets the Host can send to the Controller.
        If the Controller requires the Host to stop sending commands, the
        Num_HCI_Command_Packets event parameter will be set to zero. To indicate
        to the Host that the Controller is ready to receive HCI command packets, the
        Controller generates a Command Status event with Status 0x00 and
        Command_Opcode set to 0x0000 and the Num_HCI_Command_Packets
        event parameter set to 1 or more. Command_Opcode, 0x0000 is a NOP (No OPeration) and can be used to change
        the number of outstanding HCI command packets that the Host can send before waiting.
     */
    private short       numHciCommandPackets;
    private CommandCode commandOpCode;
}
