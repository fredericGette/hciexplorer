package fr.gette.hciexplorer.consolided;

import fr.gette.hciexplorer.hciSpecification.event.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventBloc  extends Bloc {
    private Event event;
}
