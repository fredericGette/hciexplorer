package fr.gette.hciexplorer.hciSpecification.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeSetAdvertiseEnable extends Command {
    private CommandCode opCode = CommandCode.LE_SET_ADVERTISE_ENABLE;
    private short advertisingEnable;

    public String getAdvertisingEnable() {
        String name;
        switch (advertisingEnable) {
            case 0x00 -> name = "Advertising is disabled";
            case 0x01 -> name = "Advertising is enabled";
            default -> name = " Reserved for future use";
        }
        return String.format("%s (0x%02X)", name, advertisingEnable);
    }
}
