package fr.gette.hciexplorer.hciSpecification.event;

public enum EventCode {
    INQUIRY_COMPLETE(0x01,"Indicates the Inquiry has finished."),
    INQUIRY_RESULT(0x02,"Indicates that Bluetooth device(s) have responded for the inquiry."),
    CONNECTION_COMPLETE(0x03,"Indicates to both hosts that the new connection has been formed."),
    CONNECTION_REQUEST(0x04,"Indicates that a new connection is trying to be established."),
    DISCONNECTION_COMPLETE(0x05,"Occurs when a connection has been disconnected."),
    AUTHENTICATION_COMPLETE(0x06,"Occurs when an authentication has been completed."),
    REMOTE_NAME_REQUEST_COMPLETE(0x07,"Indicates that the request for the remote name has been completed."),
    ENCRYPTION_CHANGE(0x08,"Indicates that a change in the encryption has been completed."),
    CHANGE_CONNECTION_LINK_KEY_COMPLETE(0x09,"Indicates that the change in the link key has been completed."),
    CENTRAL_LINK_KEY_COMPLETE(0x0A,"Indicates that the change in the temporary link key or semi permanent link key on the master device is complete."),
    READ_REMOTE_SUPPORTED_FEATURES_COMPLETE(0x0B,"Indicates that the reading of the supported features on the remote device is complete."),
    READ_REMOTE_VERSION_INFORMATION_COMPLETE(0x0C,"Indicates that the version number on the remote device has been read and completed."),
    QOS_SETUP_COMPLETE(0x0D,"Indicates that the Quality of Service setup has been complete."),
    COMMAND_COMPLETE(0x0E,"Used by controller to send status and event parameters to the host for the particular command."),
    COMMAND_STATUS(0x0F,"Indicates that the command has been received and is being processed in the host controller."),
    HARDWARE_ERROR(0x10,"Indicates a hardware failure of the Bluetooth device."),
    FLUSH_OCCURRED(0x11,"Indicates that the data has been flushed for a particular connection."),
    ROLE_CHANGE(0x12,"Indicates that the current bluetooth role for a connection has been changed."),
    NUMBER_OF_COMPLETED_PACKETS(0x13,"Indicates to the host the number of data packets sent compared to the last time the same event was sent."),
    MODE_CHANGE(0x14,"Indicates the change in mode from hold, sniff, park or active to another mode."),
    RETURN_LINK_KEYS(0x15,"Used to return stored link keys after a Read_Stored_Link_Key command was issued."),
    PIN_CODE_REQUEST(0x16,"Indicates the a PIN code is required for a new connection."),
    LINK_KEY_REQUEST(0x17,"Indicates that a link key is required for the connection."),
    LINK_KEY_NOTIFICATION(0x18,"Indicates to the host that a new link key has been created."),
    LOOPBACK_COMMAND(0x19,"Indicates that command sent from the host will be looped back."),
    DATA_BUFFER_OVERFLOW(0x1A,"Indicates that the data buffers on the host has overflowed."),
    MAX_SLOTS_CHANGE(0x1B,"Informs the host when the LMP_Max_Slots parameter changes."),
    READ_CLOCK_OFFSET_COMPLETE(0x1C,"Indicates the completion of reading the clock offset information."),
    CONNECTION_PACKET_TYPE_CHANGED(0x1D,"Indicate the completion of the packet type change for a connection."),
    QOS_VIOLATION(0x1E,"Indicates that the link manager is unable to provide the required Quality of Service."),
    PAGE_SCAN_MODE_CHANGE(0x1F,"Indicates that the remote device has successfully changed the Page Scan mode."),
    PAGE_SCAN_REPETITION_MODE_CHANGE(0x20,"Indicates that the remote device has successfully changed the Page Scan Repetition mode."),
    FLOW_SPECIFICATION_COMPLETE(0x21,""),
    INQUIRY_RESULT_WITH_RSSI(0x22,""),
    READ_REMOTE_EXTENDED_FEATURES_COMPLETE(0x23,""),
    SYNCHRONOUS_CONNECTION_COMPLETE(0x2C,""),
    SYNCHRONOUS_CONNECTION_CHANGED(0x2D,""),
    SNIFF_SUBRATING(0x2E,""),
    EXTENDED_INQUIRY_RESULT(0x2F,""),
    ENCRYPTION_KEY_REFRESH_COMPLETE(0x30,""),
    IO_CAPABILITY_REQUEST(0x31,""),
    IO_CAPABILITY_RESPONSE(0x32,""),
    USER_CONFIRMATION_REQUEST(0x33,""),
    USER_PASSKEY_REQUEST(0x34,""),
    REMOTE_OOB_DATA_REQUEST(0x35,""),
    SIMPLE_PAIRING_COMPLETE(0x36,""),
    LINK_SUPERVISION_TIMEOUT_CHANGED(0x38,""),
    ENHANCED_FLUSH_COMPLETE(0x39,""),
    USER_PASSKEY_NOTIFICATION(0x3B,""),
    KEYPRESS_NOTIFICATION(0x3C,""),
    REMOTE_HOST_SUPPORTED_FEATURES_NOTIFICATION(0x3D,""),
    LE_META_EVENT(0x3E,""),
    NUMBER_OF_COMPLETED_DATA_BLOCKS(0x48,""),
    BLUETOOTH_LOGO_TESTING(0xFE,""),
    VENDOR_SPECIFIC(0xFF,"");

    int code;
    String description;

    EventCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
