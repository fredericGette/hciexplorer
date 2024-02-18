package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.ClassOfDevice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WriteClassOfDevice extends Command {
    private CommandCode opCode = CommandCode.WRITE_CLASS_OF_DEVICE;
    private ClassOfDevice classOfDevice;
}
