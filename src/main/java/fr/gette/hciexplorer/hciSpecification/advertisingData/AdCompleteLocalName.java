package fr.gette.hciexplorer.hciSpecification.advertisingData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdCompleteLocalName implements AdvertisingData {
    private AdvertisingDataType advertisingDataType = AdvertisingDataType.COMPLETE_LOCAL_NAME;
    private String completeLocalName;
}
