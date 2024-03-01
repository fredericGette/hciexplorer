package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class HciMessage {
    private long id;
    private long parentId;
    private ZonedDateTime beginTimestamp;
    private ZonedDateTime endTimestamp;
}
