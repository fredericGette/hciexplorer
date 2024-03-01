package fr.gette.hciexplorer.consolided;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class Bloc {
    private ZonedDateTime timestamp;
}
