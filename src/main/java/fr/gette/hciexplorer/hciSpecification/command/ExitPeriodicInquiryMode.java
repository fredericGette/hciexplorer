package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.LAP;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExitPeriodicInquiryMode extends Command {
    private CommandCode opCode = CommandCode.EXIT_PERIODIC_INQUIRY_MODE;
}
