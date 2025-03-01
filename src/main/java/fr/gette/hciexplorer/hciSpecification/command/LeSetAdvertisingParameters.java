package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LeSetAdvertisingParameters extends Command {
    private CommandCode opCode = CommandCode.LE_SET_ADVERTISING_PARAMETERS;
    private int advertisingIntervalMin;
    private int advertisingIntervalMax;
    private short advertisingType;
    private short ownAddressType;
    private short directAddressType;
    private BluetoothAddress directAddress;
    private short advertisingChannelMap;
    private short advertisingFilterPolicy;

    public double getAdvertisingIntervalMinInMs()
    {
        return advertisingIntervalMin * 0.625;
    }
    public double getAdvertisingIntervalMaxInMs()
    {
        return advertisingIntervalMax * 0.625;
    }
}
