package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AttributeData {
    private int attributeHandle;
    private int endGroupHandle;
    private AttributeValue attributeValue;
}
