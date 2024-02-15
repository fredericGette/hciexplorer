package fr.gette.hciexplorer.hciSpecification.data;

import fr.gette.hciexplorer.hciSpecification.HciMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Data extends HciMessage {
    private HciPacketType type = HciPacketType.AclData;
}
