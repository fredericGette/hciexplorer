package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.BluetoothCoreSpecification;
import fr.gette.hciexplorer.hciSpecification.CompanyIdentifiers;
import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.NoSuchElementException;

@Getter
@Setter
@NoArgsConstructor
public class ReadLocalVersionInformationComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LOCAL_VERSION_INFORMATION;
    private ErrorCode status;
    private short hciVersion;
    private int hciSubversion;
    private short lmpVersion;
    private int companyIdentifier;
    private int lmpSubversion;

    public String getBluetoothCoreSpecification()
    {
        return BluetoothCoreSpecification.getDescription(this.lmpVersion);
    }

    public String getCompanyName()
    {
        return CompanyIdentifiers.getName(this.companyIdentifier);
    }
}
