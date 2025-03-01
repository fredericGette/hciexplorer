package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.AdvertisingOrScanResponseData;
import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeSetAdvertisingData extends Command {
    private CommandCode opCode = CommandCode.LE_SET_ADVERTISING_DATA;
    private AdvertisingOrScanResponseData advertisingData;
}
