package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

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
        computerDeviceClassValue.put(MinorDeviceClass.UNCATEGORIZED, 0);
        computerDeviceClassValue.put(MinorDeviceClass.CELLULAR, 1);
        computerDeviceClassValue.put(MinorDeviceClass.CORDLESS, 2);
        computerDeviceClassValue.put(MinorDeviceClass.SMART_PHONE, 3);
        computerDeviceClassValue.put(MinorDeviceClass.WIRED_MODEM_OR_VOICE_GATEWAY, 4);
        computerDeviceClassValue.put(MinorDeviceClass.COMMON_ISDN_ACCESS, 5);
    }

    private static final Map<MinorDeviceClass, Integer> lanDeviceClassLoadFactorValue = new HashMap<>();
    static {
        computerDeviceClassValue.put(MinorDeviceClass.FULLY_AVAILABLE, 0);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_1_TO_17_PERCENT, 1);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_17_TO_33_PERCENT, 2);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_33_TO_50_PERCENT, 3);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_50_TO_67_PERCENT, 4);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_67_TO_83_PERCENT, 5);
        computerDeviceClassValue.put(MinorDeviceClass.UTILIZED_AT_83_TO_99_PERCENT, 6);
        computerDeviceClassValue.put(MinorDeviceClass.NO_SERVICE_AVAILABLE, 7);
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


}
