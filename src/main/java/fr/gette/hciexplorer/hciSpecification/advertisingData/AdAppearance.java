package fr.gette.hciexplorer.hciSpecification.advertisingData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdAppearance implements AdvertisingData {
    private AdvertisingDataType advertisingDataType = AdvertisingDataType.APPEARANCE;
    private int appearance;

    public String getAppearance() {
        String name;
        switch(appearance) {
            case 0x03C2 -> name="Mouse";
            default -> name="Unknown";
        }
        return String.format("%s (0x%04X)", name, appearance);
    }
}
