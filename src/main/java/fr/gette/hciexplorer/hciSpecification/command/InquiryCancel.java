package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryCancel extends Command {
    private CommandCode opCode = CommandCode.INQUIRY_CANCEL;
}
