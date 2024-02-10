package fr.gette.hciexplorer.hciSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventInquiryComplete extends Event {
    private EventCode code = EventCode.INQUIRY_COMPLETE;
}
