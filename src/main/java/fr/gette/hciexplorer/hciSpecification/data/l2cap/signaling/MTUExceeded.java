package fr.gette.hciexplorer.hciSpecification.data.l2cap.signaling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MTUExceeded extends CommandReject {
    private String reasonDescription = "Signaling MTU exceeded";
    private int mtu;
}
