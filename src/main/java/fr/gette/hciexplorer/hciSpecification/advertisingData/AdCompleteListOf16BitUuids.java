package fr.gette.hciexplorer.hciSpecification.advertisingData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdCompleteListOf16BitUuids implements AdvertisingData {
    private AdvertisingDataType advertisingDataType = AdvertisingDataType.COMPLETE_LIST_OF_16_BIT_UUIDS;
    private int uuid;

    public String getUuid() {
        String name;
        switch(uuid) {
            case 0x1812 -> name="Human Interface Device";
            default -> name="Unknown";
        }
        return String.format("%s (0x%04X)", name, uuid);
    }
}
