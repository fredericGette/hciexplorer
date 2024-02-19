package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;

import java.math.BigInteger;
import java.util.*;

public class ClassOfDevice {
    BigInteger value;
    private short formatTypeField; // 0 = 1st format type

    private static final Map<MajorServiceClass, Integer> majorServiceClassBit = new HashMap<>();
    static {
        majorServiceClassBit.put(MajorServiceClass.LIMITED_DISCOVERABLE_MODE, 13);
        majorServiceClassBit.put(MajorServiceClass.POSITIONING, 16);
        majorServiceClassBit.put(MajorServiceClass.NETWORKING, 17);
        majorServiceClassBit.put(MajorServiceClass.RENDERING, 18);
        majorServiceClassBit.put(MajorServiceClass.CAPTURING, 19);
        majorServiceClassBit.put(MajorServiceClass.OBJECT_TRANSFER, 20);
        majorServiceClassBit.put(MajorServiceClass.AUDIO, 21);
        majorServiceClassBit.put(MajorServiceClass.TELEPHONY, 22);
        majorServiceClassBit.put(MajorServiceClass.INFORMATION, 23);
    }

    private static final Map<MajorDeviceClass, Integer> majorDeviceClassValue = new HashMap<>();
    static {
        majorDeviceClassValue.put(MajorDeviceClass.MISCELLANEOUS, 0);
        majorDeviceClassValue.put(MajorDeviceClass.COMPUTER, 1);
        majorDeviceClassValue.put(MajorDeviceClass.PHONE, 2);
        majorDeviceClassValue.put(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT, 3);
        majorDeviceClassValue.put(MajorDeviceClass.AUDIO_VIDEO, 4);
        majorDeviceClassValue.put(MajorDeviceClass.PERIPHERAL, 5);
        majorDeviceClassValue.put(MajorDeviceClass.IMAGING, 6);
        majorDeviceClassValue.put(MajorDeviceClass.WEARABLE, 7);
        majorDeviceClassValue.put(MajorDeviceClass.TOY, 8);
        majorDeviceClassValue.put(MajorDeviceClass.HEALTH, 9);
        majorDeviceClassValue.put(MajorDeviceClass.UNCATEGORIZED, 31);
    }

    private static final Map<MinorDeviceClass, Integer> computerDeviceClassValue = new HashMap<>();
    static {
        computerDeviceClassValue.put(MinorDeviceClass.UNCATEGORIZED, 0);
        computerDeviceClassValue.put(MinorDeviceClass.DESKTOP_WORKSTATION, 1);
        computerDeviceClassValue.put(MinorDeviceClass.SERVER_CLASS_COMPUTER, 2);
        computerDeviceClassValue.put(MinorDeviceClass.LAPTOP, 3);
        computerDeviceClassValue.put(MinorDeviceClass.HANDHELD_PC_PDA, 4);
        computerDeviceClassValue.put(MinorDeviceClass.PALM_SIZED_PC_PDA, 5);
        computerDeviceClassValue.put(MinorDeviceClass.WEARABLE_COMPUTER, 6);
    }

    private static final Map<MinorDeviceClass, Integer> phoneDeviceClassValue = new HashMap<>();
    static {
        phoneDeviceClassValue.put(MinorDeviceClass.UNCATEGORIZED, 0);
        phoneDeviceClassValue.put(MinorDeviceClass.CELLULAR, 1);
        phoneDeviceClassValue.put(MinorDeviceClass.CORDLESS, 2);
        phoneDeviceClassValue.put(MinorDeviceClass.SMART_PHONE, 3);
        phoneDeviceClassValue.put(MinorDeviceClass.WIRED_MODEM_OR_VOICE_GATEWAY, 4);
        phoneDeviceClassValue.put(MinorDeviceClass.COMMON_ISDN_ACCESS, 5);
    }

    private static final Map<MinorDeviceClass, Integer> lanDeviceClassLoadFactorValue = new HashMap<>();
    static {
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.FULLY_AVAILABLE, 0);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_1_TO_17_PERCENT, 1);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_17_TO_33_PERCENT, 2);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_33_TO_50_PERCENT, 3);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_50_TO_67_PERCENT, 4);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_67_TO_83_PERCENT, 5);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.UTILIZED_AT_83_TO_99_PERCENT, 6);
        lanDeviceClassLoadFactorValue.put(MinorDeviceClass.NO_SERVICE_AVAILABLE, 7);
    }

    private static final Map<MinorDeviceClass, Integer> audioVideoDeviceClassValue = new HashMap<>();
    static {
        audioVideoDeviceClassValue.put(MinorDeviceClass.UNCATEGORIZED, 0);
        audioVideoDeviceClassValue.put(MinorDeviceClass.WEARABLE_HEADSET_DEVICE, 1);
        audioVideoDeviceClassValue.put(MinorDeviceClass.HANDS_FREE_DEVICE, 2);
        audioVideoDeviceClassValue.put(MinorDeviceClass.MICROPHONE, 4);
        audioVideoDeviceClassValue.put(MinorDeviceClass.LOUDSPEAKER, 5);
        audioVideoDeviceClassValue.put(MinorDeviceClass.HEADPHONES, 6);
        audioVideoDeviceClassValue.put(MinorDeviceClass.PORTABLE_AUDIO, 7);
        audioVideoDeviceClassValue.put(MinorDeviceClass.CAR_AUDIO, 8);
        audioVideoDeviceClassValue.put(MinorDeviceClass.SET_TOP_BOX, 9);
        audioVideoDeviceClassValue.put(MinorDeviceClass.HIFI_AUDIO_DEVICE, 10);
        audioVideoDeviceClassValue.put(MinorDeviceClass.VCR, 11);
        audioVideoDeviceClassValue.put(MinorDeviceClass.VIDEO_CAMERA, 12);
        audioVideoDeviceClassValue.put(MinorDeviceClass.CAMCORDER, 13);
        audioVideoDeviceClassValue.put(MinorDeviceClass.VIDEO_MONITOR, 14);
        audioVideoDeviceClassValue.put(MinorDeviceClass.VIDEO_DISPLAY_AND_LOUDSPEAKER, 15);
        audioVideoDeviceClassValue.put(MinorDeviceClass.VIDEO_CONFERENCING, 16);
        audioVideoDeviceClassValue.put(MinorDeviceClass.GAMING_TOY, 18);
    }

    private static final Map<MinorDeviceClass, Integer> keyboardPointingDeviceClassValue = new HashMap<>();
    static {
        keyboardPointingDeviceClassValue.put(MinorDeviceClass.NOT_KEYBOARD_NOT_POINTING_DEVICE, 0);
        keyboardPointingDeviceClassValue.put(MinorDeviceClass.KEYBOARD, 1);
        keyboardPointingDeviceClassValue.put(MinorDeviceClass.POINTING_DEVICE, 2);
        keyboardPointingDeviceClassValue.put(MinorDeviceClass.COMBO_KEYBOARD_POINTING_DEVICE, 3);
    }

    private static final Map<MinorDeviceClass, Integer> peripheralDeviceClassValue = new HashMap<>();
    static {
        peripheralDeviceClassValue.put(MinorDeviceClass.UNCATEGORIZED, 0);
        peripheralDeviceClassValue.put(MinorDeviceClass.JOYSTICK, 1);
        peripheralDeviceClassValue.put(MinorDeviceClass.GAMEPAD, 2);
        peripheralDeviceClassValue.put(MinorDeviceClass.REMOTE_CONTROL, 3);
        peripheralDeviceClassValue.put(MinorDeviceClass.SENSING_DEVICE, 4);
        peripheralDeviceClassValue.put(MinorDeviceClass.DIGITIZER_TABLET, 5);
        peripheralDeviceClassValue.put(MinorDeviceClass.CARD_READER, 6);
    }

    private static final Map<MinorDeviceClass, Integer> imagingDeviceClassBit = new HashMap<>();
    static {
        imagingDeviceClassBit.put(MinorDeviceClass.DISPLAY, 4);
        imagingDeviceClassBit.put(MinorDeviceClass.CAMERA, 5);
        imagingDeviceClassBit.put(MinorDeviceClass.SCANNER, 6);
        imagingDeviceClassBit.put(MinorDeviceClass.PRINTER, 7);
    }

    private static final Map<MinorDeviceClass, Integer> wearableDeviceClassValue = new HashMap<>();
    static {
        wearableDeviceClassValue.put(MinorDeviceClass.WRIST_WATCH, 1);
        wearableDeviceClassValue.put(MinorDeviceClass.PAGER, 2);
        wearableDeviceClassValue.put(MinorDeviceClass.JACKET, 3);
        wearableDeviceClassValue.put(MinorDeviceClass.HELMET, 4);
        wearableDeviceClassValue.put(MinorDeviceClass.GLASSES, 5);
    }

    private static final Map<MinorDeviceClass, Integer> toyDeviceClassValue = new HashMap<>();
    static {
        toyDeviceClassValue.put(MinorDeviceClass.ROBOT, 1);
        toyDeviceClassValue.put(MinorDeviceClass.VEHICLE, 2);
        toyDeviceClassValue.put(MinorDeviceClass.DOLL_ACTION_FIGURE, 3);
        toyDeviceClassValue.put(MinorDeviceClass.CONTROLLER, 4);
        toyDeviceClassValue.put(MinorDeviceClass.GAME, 5);
    }

    private static final Map<MinorDeviceClass, Integer> healthDeviceClassValue = new HashMap<>();
    static {
        toyDeviceClassValue.put(MinorDeviceClass.UNDEFINED, 0);
        toyDeviceClassValue.put(MinorDeviceClass.BLOOD_PRESSURE_MONITOR, 1);
        toyDeviceClassValue.put(MinorDeviceClass.THERMOMETER, 2);
        toyDeviceClassValue.put(MinorDeviceClass.WEIGHING_SCALE, 3);
        toyDeviceClassValue.put(MinorDeviceClass.GLUCOSE_METER, 4);
        toyDeviceClassValue.put(MinorDeviceClass.PULSE_OXIMETER, 5);
        toyDeviceClassValue.put(MinorDeviceClass.HEART_PULSE_RATE_MONITOR, 6);
        toyDeviceClassValue.put(MinorDeviceClass.HEALTH_DATA_DISPLAY, 7);
    }

    public ClassOfDevice(IoCtlMessage data)
    {
        value = BigInteger.ZERO;
        for (int i=0; i<3; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.read1octet()).shiftLeft(i*8);
            value = value.add(octet);
        }

        formatTypeField = value.and(BigInteger.valueOf(3)).shortValue();
    }

    private boolean is(MajorServiceClass serviceClass)
    {
        Integer bit = majorServiceClassBit.get(serviceClass);
        return value.testBit(bit);
    }

    public List<MajorServiceClass> getMajorServiceClasses()
    {
        List<MajorServiceClass> majorServiceClasses = new ArrayList<>();
        majorServiceClassBit.keySet().forEach(serviceClass -> {
            if (is(serviceClass)) {
                majorServiceClasses.add(serviceClass);
            }
        });
        return majorServiceClasses;
    }

    private boolean isImagingDevice(MinorDeviceClass imagingDeviceClass)
    {
        assert Arrays.asList(imagingDeviceClass.getMajorDeviceClass()).contains(MajorDeviceClass.IMAGING);

        Integer bit = imagingDeviceClassBit.get(imagingDeviceClass);
        return value.testBit(bit);
    }

    public MajorDeviceClass getMajorDeviceClass()
    {
        BigInteger majorClassValue = value.and(BigInteger.valueOf(0x1F00)).shiftRight(8);
        for(Map.Entry<MajorDeviceClass, Integer> entry:majorDeviceClassValue.entrySet())
        {
            if (majorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("majorClassValue: %d", majorClassValue));
    }

    private MinorDeviceClass getComputerDeviceClass()
    {
        assert MajorDeviceClass.COMPUTER.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:computerDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("computerClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getPhoneDeviceClass()
    {
        assert MajorDeviceClass.PHONE.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:phoneDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("phoneClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getLanDeviceLoadFactor()
    {
        assert MajorDeviceClass.LAN_NETWORK_ACCESS_POINT.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xE0)).shiftRight(5);
        for(Map.Entry<MinorDeviceClass, Integer> entry:lanDeviceClassLoadFactorValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("lanDeviceLoadFactor: %d", minorClassValue));
    }

    private MinorDeviceClass getAudioVideoDeviceClass()
    {
        assert MajorDeviceClass.AUDIO_VIDEO.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:audioVideoDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("audioVideoClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getKeyboardPointingDeviceClass()
    {
        assert MajorDeviceClass.PERIPHERAL.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xC0)).shiftRight(6);
        for(Map.Entry<MinorDeviceClass, Integer> entry:keyboardPointingDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("keyboardPointingClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getPeripheralDeviceClass()
    {
        assert MajorDeviceClass.PERIPHERAL.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0x3C)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:peripheralDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("peripheralClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getWearableDeviceClass()
    {
        assert MajorDeviceClass.WEARABLE.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:wearableDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("wearableClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getToyDeviceClass()
    {
        assert MajorDeviceClass.TOY.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:toyDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("toyClassValue: %d", minorClassValue));
    }

    private MinorDeviceClass getHealthDeviceClass()
    {
        assert MajorDeviceClass.HEALTH.equals(getMajorDeviceClass());
        BigInteger minorClassValue = value.and(BigInteger.valueOf(0xFC)).shiftRight(2);
        for(Map.Entry<MinorDeviceClass, Integer> entry:healthDeviceClassValue.entrySet())
        {
            if (minorClassValue.intValue() == entry.getValue())
            {
                return entry.getKey();
            }
        }
        throw new NoSuchElementException(String.format("healthClassValue: %d", minorClassValue));
    }

    public List<MinorDeviceClass> getMinorDeviceClasses()
    {
        List<MinorDeviceClass> minorDeviceClasses = new ArrayList<>();

        switch(getMajorDeviceClass())
        {
            case COMPUTER -> {
                minorDeviceClasses.add(getComputerDeviceClass());
            }
            case PHONE ->  {
                minorDeviceClasses.add(getPhoneDeviceClass());
            }
            case LAN_NETWORK_ACCESS_POINT -> {
                minorDeviceClasses.add(getLanDeviceLoadFactor());
            }
            case AUDIO_VIDEO -> {
                minorDeviceClasses.add(getAudioVideoDeviceClass());
            }
            case PERIPHERAL -> {
                minorDeviceClasses.add(getKeyboardPointingDeviceClass());
                minorDeviceClasses.add(getPeripheralDeviceClass());
            }
            case IMAGING -> {
                imagingDeviceClassBit.keySet().forEach(deviceClass -> {
                    if (isImagingDevice(deviceClass))
                    {
                        minorDeviceClasses.add(deviceClass);
                    }
                });
            }
            case WEARABLE -> {
                minorDeviceClasses.add(getWearableDeviceClass());
            }
            case TOY -> {
                minorDeviceClasses.add(getToyDeviceClass());
            }
            case HEALTH -> {
                minorDeviceClasses.add(getHealthDeviceClass());
            }
        }

        return minorDeviceClasses;
    }

}
