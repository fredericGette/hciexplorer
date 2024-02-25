package fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EirCompleteLocalName implements EirData {
    private EirDataType eirDataType = EirDataType.COMPLETE_LOCAL_NAME;
    private String completeLocalName;
}
