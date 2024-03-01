package fr.gette.hciexplorer.consolided;

import fr.gette.hciexplorer.hciSpecification.data.AclData;
import fr.gette.hciexplorer.hciSpecification.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataBloc extends Bloc {
    private AclData data;
}
