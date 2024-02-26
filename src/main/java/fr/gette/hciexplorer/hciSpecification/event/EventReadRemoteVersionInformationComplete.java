package fr.gette.hciexplorer.hciSpecification.event;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import fr.gette.hciexplorer.hciSpecification.BluetoothCoreSpecification;
import fr.gette.hciexplorer.hciSpecification.CompanyIdentifiers;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.NoSuchElementException;

@Getter
@Setter
@NoArgsConstructor
public class EventReadRemoteVersionInformationComplete extends Event {
    private EventCode code = EventCode.READ_REMOTE_VERSION_INFORMATION_COMPLETE;
    private ErrorCode status;
    private int connectionHandle;
    private short version;
    private int manufacturerName;
    private int subversion;

    public String getBluetoothCoreSpecification()
    {
        return BluetoothCoreSpecification.getDescription(this.version);
    }

    public String getCompanyName()
    {
        return CompanyIdentifiers.getName(this.manufacturerName);
    }

}
