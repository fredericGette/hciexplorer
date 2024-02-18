package fr.gette.hciexplorer.hciSpecification;

public enum MinorDeviceClass {
    UNCATEGORIZED(MajorDeviceClass.COMPUTER, MajorDeviceClass.PHONE, MajorDeviceClass.LAN_NETWORK_ACCESS_POINT, MajorDeviceClass.AUDIO_VIDEO, MajorDeviceClass.PERIPHERAL, MajorDeviceClass.IMAGING),
    DESKTOP_WORKSTATION(MajorDeviceClass.COMPUTER),
    SERVER_CLASS_COMPUTER(MajorDeviceClass.COMPUTER),
    LAPTOP(MajorDeviceClass.COMPUTER),
    HANDHELD_PC_PDA(MajorDeviceClass.COMPUTER), // clam shell
    PALM_SIZED_PC_PDA(MajorDeviceClass.COMPUTER),
    WEARABLE_COMPUTER(MajorDeviceClass.COMPUTER), // Watch sized
    CELLULAR(MajorDeviceClass.PHONE),
    CORDLESS(MajorDeviceClass.PHONE),
    SMART_PHONE(MajorDeviceClass.PHONE),
    WIRED_MODEM_OR_VOICE_GATEWAY(MajorDeviceClass.PHONE),
    COMMON_ISDN_ACCESS(MajorDeviceClass.PHONE),
    FULLY_AVAILABLE(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_1_TO_17_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_17_TO_33_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_33_TO_50_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_50_TO_67_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_67_TO_83_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    UTILIZED_AT_83_TO_99_PERCENT(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    NO_SERVICE_AVAILABLE(MajorDeviceClass.LAN_NETWORK_ACCESS_POINT),
    WEARABLE_HEADSET_DEVICE(MajorDeviceClass.AUDIO_VIDEO),
    HANDS_FREE_DEVICE(MajorDeviceClass.AUDIO_VIDEO),
    MICROPHONE(MajorDeviceClass.AUDIO_VIDEO),
    LOUDSPEAKER(MajorDeviceClass.AUDIO_VIDEO),
    HEADPHONES(MajorDeviceClass.AUDIO_VIDEO),
    PORTABLE_AUDIO(MajorDeviceClass.AUDIO_VIDEO),
    CAR_AUDIO(MajorDeviceClass.AUDIO_VIDEO),
    SET_TOP_BOX(MajorDeviceClass.AUDIO_VIDEO),
    HIFI_AUDIO_DEVICE(MajorDeviceClass.AUDIO_VIDEO),
    VCR(MajorDeviceClass.AUDIO_VIDEO),
    VIDEO_CAMERA(MajorDeviceClass.AUDIO_VIDEO),
    CAMCORDER(MajorDeviceClass.AUDIO_VIDEO),
    VIDEO_MONITOR(MajorDeviceClass.AUDIO_VIDEO),
    VIDEO_DISPLAY_AND_LOUDSPEAKER(MajorDeviceClass.AUDIO_VIDEO),
    VIDEO_CONFERENCING(MajorDeviceClass.AUDIO_VIDEO),
    GAMING_TOY(MajorDeviceClass.AUDIO_VIDEO),
    NOT_KEYBOARD_NOT_POINTING_DEVICE(MajorDeviceClass.PERIPHERAL),
    KEYBOARD(MajorDeviceClass.PERIPHERAL),
    POINTING_DEVICE(MajorDeviceClass.PERIPHERAL),
    COMBO_KEYBOARD_POINTING_DEVICE(MajorDeviceClass.PERIPHERAL),
    JOYSTICK(MajorDeviceClass.PERIPHERAL),
    GAMEPAD(MajorDeviceClass.PERIPHERAL),
    REMOTE_CONTROL(MajorDeviceClass.PERIPHERAL),
    SENSING_DEVICE(MajorDeviceClass.PERIPHERAL),
    DIGITIZER_TABLET(MajorDeviceClass.PERIPHERAL),
    CARD_READER(MajorDeviceClass.PERIPHERAL), // e.g. SIM Card Reader
    DISPLAY(MajorDeviceClass.IMAGING),
    CAMERA(MajorDeviceClass.IMAGING),
    SCANNER(MajorDeviceClass.IMAGING),
    PRINTER(MajorDeviceClass.IMAGING),
    WRIST_WATCH(MajorDeviceClass.WEARABLE),
    PAGER(MajorDeviceClass.WEARABLE),
    JACKET(MajorDeviceClass.WEARABLE),
    HELMET(MajorDeviceClass.WEARABLE),
    GLASSES(MajorDeviceClass.WEARABLE),
    ROBOT(MajorDeviceClass.TOY),
    VEHICLE(MajorDeviceClass.TOY),
    DOLL_ACTION_FIGURE(MajorDeviceClass.TOY),
    CONTROLLER(MajorDeviceClass.TOY),
    GAME(MajorDeviceClass.TOY),
    UNDEFINED(MajorDeviceClass.HEALTH),
    BLOOD_PRESSURE_MONITOR(MajorDeviceClass.HEALTH),
    THERMOMETER(MajorDeviceClass.HEALTH),
    WEIGHING_SCALE(MajorDeviceClass.HEALTH),
    GLUCOSE_METER(MajorDeviceClass.HEALTH),
    PULSE_OXIMETER(MajorDeviceClass.HEALTH),
    HEART_PULSE_RATE_MONITOR(MajorDeviceClass.HEALTH),
    HEALTH_DATA_DISPLAY(MajorDeviceClass.HEALTH);

    private MajorDeviceClass[] majorDeviceClass;

    MinorDeviceClass(MajorDeviceClass ... majorDeviceClass)
    {
        this.majorDeviceClass = majorDeviceClass;
    }
}
