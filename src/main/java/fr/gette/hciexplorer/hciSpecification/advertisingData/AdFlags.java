package fr.gette.hciexplorer.hciSpecification.advertisingData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdFlags implements AdvertisingData {
    private AdvertisingDataType advertisingDataType = AdvertisingDataType.FLAGS;
    private short flags;
}
