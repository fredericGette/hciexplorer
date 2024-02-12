package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataFailed extends HciMessage {
    private HciPacketType type = HciPacketType.AclData;
    private IoCtlStatus ioCtlStatus;
}
