package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HostBufferSize extends Command {
    private CommandCode opCode = CommandCode.HOST_BUFFER_SIZE;
    private int hostACLDataPacketLength;
    private short hostSynchronousDataPacketLength;
    private int hostTotalNumACLDataPackets;
    private int hostTotalNumSynchronousDataPackets;
}
