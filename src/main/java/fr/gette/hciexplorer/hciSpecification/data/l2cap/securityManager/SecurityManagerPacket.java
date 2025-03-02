package fr.gette.hciexplorer.hciSpecification.data.l2cap.securityManager;

import fr.gette.hciexplorer.hciSpecification.data.l2cap.Frame;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class SecurityManagerPacket extends Frame {
    public abstract SecurityManagerCommandCode getCommandCode();
}
