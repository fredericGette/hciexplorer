package fr.gette.hciexplorer.hciSpecification.data;

import fr.gette.hciexplorer.service.IoCtlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AclDataUnfinished extends AclData {
    private IoCtlStatus ioCtlStatus;
}
