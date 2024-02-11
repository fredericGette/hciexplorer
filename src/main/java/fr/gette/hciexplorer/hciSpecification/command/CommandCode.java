package fr.gette.hciexplorer.hciSpecification.command;

public enum CommandCode {
    LINK_CONTROL_INQUIRY(0x0401,"Command used to enter Inquiry mode where it discovers other Bluetooth devices."),
    LINK_CONTROL_INQUIRY_CANCEL(0x0402,"Command to cancel the Inquiry mode in which the Bluetooth device is in."),
    LINK_CONTROL_PERIODIC_INQUIRY_MODE(0x0403,"Command to set the device to enter Inquiry modes periodically according to the time interval set."),
    LINK_CONTROL_EXIT_PERIODIC_INQUIRY_MODE(0x0404,"Command to exit the periodic Inquiry mode"),
    LINK_CONTROL_CREATE_CONNECTION(0x0405,"Command to create an ACL connection to the device specified by the BD_ADDR in the parameters."),
    LINK_CONTROL_DISCONNECT(0x0406," Command to terminate the existing connection to a device"),
    LINK_CONTROL_ADD_SCO_CONNECTION(0x0407,"Create an SCO connection defined by the connection handle parameters."),
    LINK_CONTROL_CREATE_CONNECTION_CANCEL(0x0408,""),
    LINK_CONTROL_ACCEPT_CONNECTION_REQUEST(0x0409,"Command to accept a new connection request"),
    LINK_CONTROL_REJECT_CONNECTION_REQUEST(0x040A,"Command to reject a new connection request"),
    LINK_CONTROL_LINK_KEY_REQUEST_REPLY(0x040B,"Reply command to a link key request event sent from controller to the host"),
    LINK_CONTROL_LINK_KEY_REQUEST_NEGATIVE_REPLY(0x040C,"Reply command to a link key request event from the controller to the host if there is no link key associated with the connection."),
    LINK_CONTROL_PIN_CODE_REQUEST_REPLY(0x040D,"Reply command to a PIN code request event sent from a controller to the host."),
    LINK_CONTROL_PIN_CODE_REQUEST_NEGATIVE_REPLY(0x040E,"Reply command to a PIN code request event sent from the controller to the host if there is no PIN associated with the connection."),
    LINK_CONTROL_CHANGE_CONNECTION_PACKET_TYPE(0x040F,"Command to change the type of packets to be sent for an existing connection."),
    LINK_CONTROL_AUTHENTICATION_REQUESTED(0x0411,"Command to establish authentication between two devices specified by the connection handle."),
    LINK_CONTROL_SET_CONNECTION_ENCRYPTION(0x0413,"Command to enable or disable the link level encryption."),
    LINK_CONTROL_CHANGE_CONNECTION_LINK_KEY(0x0415,"Command to force the change of a link key to a new one between two connected devices."),
    LINK_CONTROL_CENTRAL_LINK_KEY(0x0417,"Command to force two devices to use the master's link key temporarily."),
    LINK_CONTROL_REMOTE_NAME_REQUEST(0x0419,"Command to determine the user friendly name of the connected device."),
    LINK_CONTROL_REMOTE_NAME_REQUEST_CANCEL(0x041A,""),
    LINK_CONTROL_READ_REMOTE_SUPPORTED_FEATURES(0x041B,"Command to determine the features supported by the connected device."),
    LINK_CONTROL_READ_REMOTE_EXTENDED_FEATURES(0x041C,""),
    LINK_CONTROL_READ_REMOTE_VERSION_INFORMATION(0x041D,"Command to determine the version information of the connected device."),
    LINK_CONTROL_READ_CLOCK_OFFSET(0x041F,"Command to read the clock offset of the remote device."),
    LINK_CONTROL_READ_LMP_HANDLE(0x0420,""),
    LINK_CONTROL_SETUP_SYNCHRONOUS_CONNECTION(0x0428,""),
    LINK_CONTROL_ACCEPT_SYNCHRONOUS_CONNECTION(0x0429,""),
    LINK_CONTROL_REJECT_SYNCHRONOUS_CONNECTION(0x042A,""),
    LINK_CONTROL_IO_CAPABILITY_REQUEST_REPLY(0x042B,""),
    LINK_CONTROL_USER_CONFIRMATION_REQUEST_REPLY(0x042C,""),
    LINK_CONTROL_USER_CONFIRMATION_REQUEST_NEGATIVE_REPLY(0x042D,""),
    LINK_CONTROL_USER_PASSKEY_REQUEST_REPLY(0x042E,""),
    LINK_CONTROL_USER_PASSKEY_REQUEST_NEGATIVE_REPLY(0x042F,""),
    LINK_CONTROL_REMOTE_OOB_DATA_REQUEST_REPLY(0x0430,""),
    LINK_CONTROL_REMOTE_OOB_DATA_REQUEST_NEGATIVE_REPLY(0x0433,""),
    LINK_CONTROL_IO_CAPABILITY_REQUEST_NEGATIVE_REPLY(0x0434,""),
    LINK_CONTROL_ENHANCED_SETUP_SYNCHRONOUS_CONNECTION(0x043D,""),
    LINK_CONTROL_ENHANCED_ACCEPT_SYNCHRONOUS_CONNECTION(0x043E,""),
    LINK_CONTROL_REMOTE_OOB_EXTENDED_DATA_REQUEST_REPLY(0x0445,""),
    LINK_POLICY_HOLD_MODE(0x0801,"Command to place the current or remote device into the Hold mode state."),
    LINK_POLICY_SNIFF_MODE(0x0803,"Command to place the current or remote device into the Sniff mode state."),
    LINK_POLICY_EXIT_SNIFF_MODE(0x0804,"Command to exit the current or remote device from the Sniff mode state."),
    LINK_POLICY_PARK_MODE(0x0805,"Command to place the current or remote device into the Park mode state."),
    LINK_POLICY_EXIT_PARK_MODE(0x0806,"Command to exit the current or remote device from the Park mode state."),
    LINK_POLICY_QOS_SETUP(0x0807,"Command to setup the Quality of Service parameters of the device."),
    LINK_POLICY_ROLE_DISCOVERY(0x0809,"Command to determine the role of the device for a particular connection."),
    LINK_POLICY_SWITCH_ROLE(0x080B,"Command to allow the device to switch roles for a particular connection."),
    LINK_POLICY_READ_LINK_POLICY_SETTINGS(0x080C,"Command to determine the link policy that the LM can use to establish connections."),
    LINK_POLICY_WRITE_LINK_POLICY_SETTINGS(0x080D,"Command to set the link policy that the LM can use for a particular connection."),
    LINK_POLICY_READ_DEFAULT_LINK_POLICY_SETTINGS(0x080E,""),
    LINK_POLICY_WRITE_DEFAULT_LINK_POLICY_SETTINGS(0x080F,""),
    LINK_POLICY_FLOW_SPECIFICATION(0x0810,""),
    LINK_POLICY_SNIFF_SUBRATING(0x0811,""),
    CONTROLLER_AND_BASEBAND_SET_EVENT_MASK(0x0C01,"Command to set which events are generated by the HCI for the host."),
    CONTROLLER_AND_BASEBAND_RESET(0x0C03,"Command to reset the host controller, link manager and the radio module."),
    CONTROLLER_AND_BASEBAND_SET_EVENT_FILTER(0x0C05,"Command used by host to set the different types of event filters that the host needs to receive."),
    CONTROLLER_AND_BASEBAND_FLUSH(0x0C08,"Command used to flush all pending data packets for transmission for a particular connection handle."),
    CONTROLLER_AND_BASEBAND_READ_PIN_TYPE(0x0C09,"Command used by host to determine if the link manager assumes that the host requires a variable PIN type or fixed PIN code. PIN is used during pairing."),
    CONTROLLER_AND_BASEBAND_WRITE_PIN_TYPE(0x0C0A,"Command used by host to write to the host controller on the PIN type supported by the host."),
    CONTROLLER_AND_BASEBAND_CREATE_NEW_UNIT_KEY(0x0C0B,"Command used to create a new unit key."),
    CONTROLLER_AND_BASEBAND_READ_STORED_LINK_KEY(0x0C0D,"Command to read the link key stored in the host controller."),
    CONTROLLER_AND_BASEBAND_WRITE_STORED_LINK_KEY(0x0C11,"Command to write the link key to the host controller."),
    CONTROLLER_AND_BASEBAND_DELETE_STORED_LINK_KEY(0x0C12,"Command to delete a stored link key in the host controller."),
    CONTROLLER_AND_BASEBAND_WRITE_LOCAL_NAME(0x0C13,"Command to modify the user friendly name of the device."),
    CONTROLLER_AND_BASEBAND_READ_LOCAL_NAME(0x0C14,"Command to read the user friendly name of the device."),
    CONTROLLER_AND_BASEBAND_READ_CONNECTION_ACCEPT_TIMEOUT(0x0C15,"Command to determine the timeout session before the host denies and rejects a new connection request."),
    CONTROLLER_AND_BASEBAND_WRITE_CONNECTION_ACCEPT_TIMEOUT(0x0C16,"Command to set the timeout session before a device can deny or reject a connection request."),
    CONTROLLER_AND_BASEBAND_READ_PAGE_TIMEOUT(0x0C17,"Command to read the timeout value where a device will wait for a connection acceptance before sending a connection failure is returned."),
    CONTROLLER_AND_BASEBAND_WRITE_PAGE_TIMEOUT(0x0C18,"Command to write the timeout value where a device will wait for a connection acceptance before sending a connection failure is returned."),
    CONTROLLER_AND_BASEBAND_READ_SCAN_ENABLE(0x0C19,"Command to read the status of the Scan_Enable configuration."),
    CONTROLLER_AND_BASEBAND_WRITE_SCAN_ENABLE(0x0C1A,"Command to set the status of the Scan_Enable configuration."),
    CONTROLLER_AND_BASEBAND_READ_PAGE_SCAN_ACTIVITY(0x0C1B,"Command to read the value of the Page_Scan_Interval and Page_Scan_Window configurations."),
    CONTROLLER_AND_BASEBAND_WRITE_PAGE_SCAN_ACTIVITY(0x0C1C,"Command to write the value of the Page_Scan_Interval and Page_Scan_Window configurations."),
    CONTROLLER_AND_BASEBAND_READ_INQUIRY_SCAN_ACTIVITY(0x0C1D,"Command to read the value of the Inquiry_Scan_Interval and Inquiry_Scan_Window configurations."),
    CONTROLLER_AND_BASEBAND_WRITE_INQUIRY_SCAN_ACTIVITY(0x0C1E,"Command to set the value of the Inquiry_Scan_Interval and Inquiry_Scan_Window configurations."),
    CONTROLLER_AND_BASEBAND_READ_AUTHENTICATION_ENABLE(0x0C1F,"Command to read the Authentication_Enable parameter."),
    CONTROLLER_AND_BASEBAND_WRITE_AUTHENTICATION_ENABLE(0x0C20,"Command to set the Authentication_Enable parameter."),
    CONTROLLER_AND_BASEBAND_READ_ENCRYPTION_MODE(0x0C21,"Command to read the Encryption_Mode parameter."),
    CONTROLLER_AND_BASEBAND_WRITE_ENCRYPTION_MODE(0x0C22,"Command to write the Encryption_Mode parameter."),
    CONTROLLER_AND_BASEBAND_READ_CLASS_OF_DEVICE(0x0C23,"Command to read the Class_Of_Device parameter."),
    CONTROLLER_AND_BASEBAND_WRITE_CLASS_OF_DEVICE(0x0C24,"Command to set the Class_Of_Device parameter."),
    CONTROLLER_AND_BASEBAND_READ_VOICE_SETTING(0x0C25,"Command to read the Voice_Setting parameter. Used for voice connections."),
    CONTROLLER_AND_BASEBAND_WRITE_VOICE_SETTING(0x0C26,"Command to set the Voice_Setting parameter. Used for voice connections."),
    CONTROLLER_AND_BASEBAND_READ_AUTOMATIC_FLUSH_TIMEOUT(0x0C27,"Command to read the Flush_Timeout parameter. Used for ACL connections only."),
    CONTROLLER_AND_BASEBAND_WRITE_AUTOMATIC_FLUSH_TIMEOUT(0x0C28,"Command to set the Flush_Timeout parameter. Used for ACL connections only."),
    CONTROLLER_AND_BASEBAND_READ_NUM_BROADCAST_RETRANSMITS(0x0C29,"Command to read the number of time a broadcast message is retransmitted."),
    CONTROLLER_AND_BASEBAND_WRITE_NUM_BROADCAST_RETRANSMITS(0x0C2A,"Command to set the number of time a broadcast message is retransmitted."),
    CONTROLLER_AND_BASEBAND_READ_HOLD_MODE_ACTIVITY(0x0C2B,"Command to set the Hold_Mode activity to instruct the device to perform an activity during hold mode."),
    CONTROLLER_AND_BASEBAND_WRITE_HOLD_MODE_ACTIVITY(0x0C2C,"Command to set the Hold_Mode_Activity parameter."),
    CONTROLLER_AND_BASEBAND_READ_TRANSMIT_POWER_LEVEL(0x0C2D,"Command to read the power level required for transmission for a connection handle."),
    CONTROLLER_AND_BASEBAND_READ_SYNCHRONOUS_FLOW_CONTROL_ENABLE(0x0C2E,"Command to check the current status of the flow control for the SCO connection."),
    CONTROLLER_AND_BASEBAND_WRITE_SYNCHRONOUS_FLOW_CONTROL_ENABLE(0x0C2F,"Command to set the status of the flow control for a connection handle."),
    CONTROLLER_AND_BASEBAND_SET_CONTROLLER_TO_HOST_FLOW_CONTROL(0x0C31,"Command to set the flow control from the host controller to host in on or off state."),
    CONTROLLER_AND_BASEBAND_HOST_BUFFER_SIZE(0x0C33,"Command set by host to inform the host controller of the buffer size of the host for ACL and SCO connections."),
    CONTROLLER_AND_BASEBAND_HOST_NUM_COMPLETED_PACKETS(0x0C35,"Command set from host to host controller when it is ready to receive more data packets."),
    CONTROLLER_AND_BASEBAND_READ_LINK_SUPERVISION_TIMEOUT(0x0C36,"Command to read the timeout for monitoring link losses."),
    CONTROLLER_AND_BASEBAND_WRITE_LINK_SUPERVISION_TIMEOUT(0x0C37,"Command to set the timeout for monitoring link losses."),
    CONTROLLER_AND_BASEBAND_READ_NUMBER_OF_SUPPORTED_IAC(0x0C38,"Command to read the number of IACs that the device can listen on during Inquiry access."),
    CONTROLLER_AND_BASEBAND_READ_CURRENT_IAC_LAP(0x0C39,"Command to read the LAP for the current IAC."),
    CONTROLLER_AND_BASEBAND_WRITE_CURRENT_IAC_LAP(0x0C3A,"Command to set the LAP for the current IAC."),
    CONTROLLER_AND_BASEBAND_READ_PAGE_SCAN_PERIOD_MODE(0x0C3B,"Command to read the timeout session of a page scan."),
    CONTROLLER_AND_BASEBAND_WRITE_PAGE_SCAN_PERIOD_MODE(0x0C3C,"Command to set the timeout session of a page scan."),
    CONTROLLER_AND_BASEBAND_READ_PAGE_SCAN_MODE(0x0C3D,"Command to read the default Page scan mode."),
    CONTROLLER_AND_BASEBAND_WRITE_PAGE_SCAN_MODE(0x0C3E,"Command to set the default page scan mode."),
    CONTROLLER_AND_BASEBAND_SET_AFH_HOST_CHANNEL_CLASSIFICATION(0x0C3F,""),
    CONTROLLER_AND_BASEBAND_READ_INQUIRY_SCAN_TYPE(0x0C42,""),
    CONTROLLER_AND_BASEBAND_WRITE_INQUIRY_SCAN_TYPE(0x0C43,""),
    CONTROLLER_AND_BASEBAND_READ_INQUIRY_MODE(0x0C44,""),
    CONTROLLER_AND_BASEBAND_WRITE_INQUIRY_MODE(0x0C45,""),
    CONTROLLER_AND_BASEBAND_READ_PAGE_SCAN_TYPE(0x0C46,""),
    CONTROLLER_AND_BASEBAND_WRITE_PAGE_SCAN_TYPE(0x0C47,""),
    CONTROLLER_AND_BASEBAND_READ_AFH_CHANNEL_ASSESSMENT_MODE(0x0C48,""),
    CONTROLLER_AND_BASEBAND_WRITE_AFH_CHANNEL_ASSESSMENT_MODE(0x0C49,""),
    CONTROLLER_AND_BASEBAND_READ_EXTENDED_INQUIRY_RESPONSE(0x0C51,""),
    CONTROLLER_AND_BASEBAND_WRITE_EXTENDED_INQUIRY_RESPONSE(0x0C52,""),
    CONTROLLER_AND_BASEBAND_REFRESH_ENCRYPTION_KEY(0x0C53,""),
    CONTROLLER_AND_BASEBAND_READ_SIMPLE_PAIRING_MODE(0x0C55,""),
    CONTROLLER_AND_BASEBAND_WRITE_SIMPLE_PAIRING_MODE(0x0C56,""),
    CONTROLLER_AND_BASEBAND_READ_LOCAL_OOB_DATA(0x0C57,""),
    CONTROLLER_AND_BASEBAND_READ_INQUIRY_RESPONSE_TRANSMIT_POWER_LEVEL(0x0C58,""),
    CONTROLLER_AND_BASEBAND_WRITE_INQUIRY_TRANSMIT_POWER_LEVEL(0x0C59,""),
    CONTROLLER_AND_BASEBAND_ENHANCED_FLUSH(0x0C5F,""),
    CONTROLLER_AND_BASEBAND_SEND_KEYPRESS_NOTIFICATION(0x0C60,""),
    CONTROLLER_AND_BASEBAND_READ_LE_HOST_SUPPORT(0x0C6C,""),
    CONTROLLER_AND_BASEBAND_WRITE_LE_HOST_SUPPORT(0x0C6D,""),
    CONTROLLER_AND_BASEBAND_READ_SECURE_CONNECTIONS_HOST_SUPPORT(0x0C79,""),
    CONTROLLER_AND_BASEBAND_WRITE_SECURE_CONNECTIONS_HOST_SUPPORT(0x0C7A,""),
    CONTROLLER_AND_BASEBAND_READ_LOCAL_OOB_EXTENDED_DATA(0x0C7D,""),
    CONTROLLER_AND_BASEBAND_SET_ECOSYSTEM_BASE_INTERVAL(0x0C82,""),
    CONTROLLER_AND_BASEBAND_CONFIGURE_DATA_PATH(0x0C83,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_VERSION_INFORMATION(0x1001,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_COMMANDS(0x1002,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_FEATURES(0x1003,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_EXTENDED_FEATURES(0x1004,""),
    INFORMATIONAL_PARAMETERS_READ_BUFFER_SIZE(0x1005,""),
    INFORMATIONAL_PARAMETERS_READ_COUNTRY_CODE(0x1007,""),
    INFORMATIONAL_PARAMETERS_READ_BD_ADDR(0x1009,""),
    INFORMATIONAL_PARAMETERS_READ_DATA_BLOCK_SIZE(0x100A,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_CODECS_V1(0x100B,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_CODECS_V2(0x100D,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_CODEC_CAPABILITIES(0x100E,""),
    INFORMATIONAL_PARAMETERS_READ_LOCAL_SUPPORTED_CONTROLLER_DELAY(0x100F,""),
    STATUS_PARAMETERS_READ_FAILED_CONTACT_COUNTER(0x1401,""),
    STATUS_PARAMETERS_RESET_FAILED_CONTACT_COUNTER(0x1402,""),
    STATUS_PARAMETERS_READ_LINK_QUALITY(0x1403,""),
    STATUS_PARAMETERS_READ_RSSI(0x1405,""),
    STATUS_PARAMETERS_READ_AFH_CHANNEL_MAP(0x1406,""),
    STATUS_PARAMETERS_READ_CLOCK(0x1407,""),
    STATUS_PARAMETERS_READ_ENCRYPTION_KEY_SIZE(0x1408,""),
    TESTING_READ_LOOPBACK_MODE(0x1801,""),
    TESTING_WRITE_LOOPBACK_MODE(0x1802,""),
    TESTING_ENABLE_DEVICE_UNDER_TEST_MODE(0x1803,""),
    TESTING_WRITE_SIMPLE_PAIRING_DEBUG_MODE(0x1804,""),
    TESTING_WRITE_SECURE_CONNECTIONS_TEST_MODE(0x180A,""),
    LE_CONTROLLER_LE_SET_EVENT_MASK(0x2001,""),
    LE_CONTROLLER_LE_READ_BUFFER_SIZE_V1(0x2002,""),
    LE_CONTROLLER_LE_READ_LOCAL_SUPPORTED_FEATURES(0x2003,""),
    LE_CONTROLLER_LE_SET_RANDOM_ADDRESS(0x2005,""),
    LE_CONTROLLER_LE_SET_ADVERTISING_PARAMETERS(0x2006,""),
    LE_CONTROLLER_LE_READ_ADVERTISING_PHYSICAL_CHANNEL_TX_POWER(0x2007,""),
    LE_CONTROLLER_LE_SET_ADVERTISING_DATA(0x2008,""),
    LE_CONTROLLER_LE_SET_SCAN_RESPONSE_DATA(0x2009,""),
    LE_CONTROLLER_LE_SET_ADVERTISING_ENABLE(0x200A,""),
    LE_CONTROLLER_LE_SET_SCAN_PARAMETERS(0x200B,""),
    LE_CONTROLLER_LE_SET_SCAN_ENABLE(0x200C,""),
    LE_CONTROLLER_LE_CREATE_CONNECTION(0x200D,""),
    LE_CONTROLLER_LE_CREATE_CONNECTION_CANCEL(0x200E,""),
    LE_CONTROLLER_LE_READ_CONNECT_LIST_SIZE(0x200F,""),
    LE_CONTROLLER_LE_CLEAR_CONNECT_LIST(0x2010,""),
    LE_CONTROLLER_LE_ADD_DEVICE_TO_CONNECT_LIST(0x2011,""),
    LE_CONTROLLER_LE_REMOVE_DEVICE_FROM_CONNECT_LIST(0x2012,""),
    LE_CONTROLLER_LE_CONNECTION_UPDATE(0x2013,""),
    LE_CONTROLLER_LE_SET_HOST_CHANNEL_CLASSIFICATION(0x2014,""),
    LE_CONTROLLER_LE_READ_CHANNEL_MAP(0x2015,""),
    LE_CONTROLLER_LE_READ_REMOTE_FEATURES(0x2016,""),
    LE_CONTROLLER_LE_ENCRYPT(0x2017,""),
    LE_CONTROLLER_LE_RAND(0x2018,""),
    LE_CONTROLLER_LE_START_ENCRYPTION(0x2019,""),
    LE_CONTROLLER_LE_LONG_TERM_KEY_REQUEST_REPLY(0x201A,""),
    LE_CONTROLLER_LE_LONG_TERM_KEY_REQUEST_NEGATIVE_REPLY(0x201B,""),
    LE_CONTROLLER_LE_READ_SUPPORTED_STATES(0x201C,""),
    LE_CONTROLLER_LE_RECEIVER_TEST(0x201D,""),
    LE_CONTROLLER_LE_TRANSMITTER_TEST(0x201E,""),
    LE_CONTROLLER_LE_TEST_END(0x201F,""),
    LE_CONTROLLER_LE_REMOTE_CONNECTION_PARAMETER_REQUEST_REPLY(0x2020,""),
    LE_CONTROLLER_LE_REMOTE_CONNECTION_PARAMETER_REQUEST_NEGATIVE_REPLY(0x2021,""),
    LE_CONTROLLER_LE_SET_DATA_LENGTH(0x2022,""),
    LE_CONTROLLER_LE_READ_SUGGESTED_DEFAULT_DATA_LENGTH(0x2023,""),
    LE_CONTROLLER_LE_WRITE_SUGGESTED_DEFAULT_DATA_LENGTH(0x2024,""),
    LE_CONTROLLER_LE_READ_LOCAL_P_256_PUBLIC_KEY_COMMAND(0x2025,""),
    LE_CONTROLLER_LE_GENERATE_DHKEY_COMMAND_V1(0x2026,""),
    LE_CONTROLLER_LE_ADD_DEVICE_TO_RESOLVING_LIST(0x2027,""),
    LE_CONTROLLER_LE_REMOVE_DEVICE_FROM_RESOLVING_LIST(0x2028,""),
    LE_CONTROLLER_LE_CLEAR_RESOLVING_LIST(0x2029,""),
    LE_CONTROLLER_LE_READ_RESOLVING_LIST_SIZE(0x202A,""),
    LE_CONTROLLER_LE_READ_PEER_RESOLVABLE_ADDRESS(0x202B,""),
    LE_CONTROLLER_LE_READ_LOCAL_RESOLVABLE_ADDRESS(0x202C,""),
    LE_CONTROLLER_LE_SET_ADDRESS_RESOLUTION_ENABLE(0x202D,""),
    LE_CONTROLLER_LE_SET_RESOLVABLE_PRIVATE_ADDRESS_TIMEOUT(0x202E,""),
    LE_CONTROLLER_LE_READ_MAXIMUM_DATA_LENGTH(0x202F,""),
    LE_CONTROLLER_LE_READ_PHY(0x2030,""),
    LE_CONTROLLER_LE_SET_DEFAULT_PHY(0x2031,""),
    LE_CONTROLLER_LE_SET_PHY(0x2032,""),
    LE_CONTROLLER_LE_ENHANCED_RECEIVER_TEST(0x2033,""),
    LE_CONTROLLER_LE_ENHANCED_TRANSMITTER_TEST(0x2034,""),
    LE_CONTROLLER_LE_SET_EXTENDED_ADVERTISING_RANDOM_ADDRESS(0x2035,""),
    LE_CONTROLLER_LE_SET_EXTENDED_ADVERTISING_PARAMETERS(0x2036,""),
    LE_CONTROLLER_LE_SET_EXTENDED_ADVERTISING_DATA(0x2037,""),
    LE_CONTROLLER_LE_SET_EXTENDED_ADVERTISING_SCAN_RESPONSE(0x2038,""),
    LE_CONTROLLER_LE_SET_EXTENDED_ADVERTISING_ENABLE(0x2039,""),
    LE_CONTROLLER_LE_READ_MAXIMUM_ADVERTISING_DATA_LENGTH(0x203A,""),
    LE_CONTROLLER_LE_READ_NUMBER_OF_SUPPORTED_ADVERTISING_SETS(0x203B,""),
    LE_CONTROLLER_LE_REMOVE_ADVERTISING_SET(0x203C,""),
    LE_CONTROLLER_LE_CLEAR_ADVERTISING_SETS(0x203D,""),
    LE_CONTROLLER_LE_SET_PERIODIC_ADVERTISING_PARAM(0x203E,""),
    LE_CONTROLLER_LE_SET_PERIODIC_ADVERTISING_DATA(0x203F,""),
    LE_CONTROLLER_LE_SET_PERIODIC_ADVERTISING_ENABLE(0x2040,""),
    LE_CONTROLLER_LE_SET_EXTENDED_SCAN_PARAMETERS(0x2041,""),
    LE_CONTROLLER_LE_SET_EXTENDED_SCAN_ENABLE(0x2042,""),
    LE_CONTROLLER_LE_EXTENDED_CREATE_CONNECTION(0x2043,""),
    LE_CONTROLLER_LE_PERIODIC_ADVERTISING_CREATE_SYNC(0x2044,""),
    LE_CONTROLLER_LE_PERIODIC_ADVERTISING_CREATE_SYNC_CANCEL(0x2045,""),
    LE_CONTROLLER_LE_PERIODIC_ADVERTISING_TERMINATE_SYNC(0x2046,""),
    LE_CONTROLLER_LE_ADD_DEVICE_TO_PERIODIC_ADVERTISING_LIST(0x2047,""),
    LE_CONTROLLER_LE_REMOVE_DEVICE_FROM_PERIODIC_ADVERTISING_LIST(0x2048,""),
    LE_CONTROLLER_LE_CLEAR_PERIODIC_ADVERTISING_LIST(0x2049,""),
    LE_CONTROLLER_LE_READ_PERIODIC_ADVERTISING_LIST_SIZE(0x204A,""),
    LE_CONTROLLER_LE_READ_TRANSMIT_POWER(0x204B,""),
    LE_CONTROLLER_LE_READ_RF_PATH_COMPENSATION_POWER(0x204C,""),
    LE_CONTROLLER_LE_WRITE_RF_PATH_COMPENSATION_POWER(0x204D,""),
    LE_CONTROLLER_LE_SET_PRIVACY_MODE(0x204E,""),
    LE_CONTROLLER_LE_SET_PERIODIC_ADVERTISING_RECEIVE_ENABLE(0x2059,""),
    LE_CONTROLLER_LE_PERIODIC_ADVERTISING_SYNC_TRANSFER(0x205A,""),
    LE_CONTROLLER_LE_PERIODIC_ADVERTISING_SET_INFO_TRANSFER(0x205B,""),
    LE_CONTROLLER_LE_SET_PERIODIC_ADVERTISING_SYNC_TRANSFER_PARAMETERS(0x205C,""),
    LE_CONTROLLER_LE_SET_DEFAULT_PERIODIC_ADVERTISING_SYNC_TRANSFER_PARAMETERS(0x205D,""),
    LE_CONTROLLER_LE_GENERATE_DHKEY_COMMAND(0x205E,""),
    LE_CONTROLLER_LE_MODIFY_SLEEP_CLOCK_ACCURACY(0x205F,""),
    LE_CONTROLLER_LE_READ_BUFFER_SIZE_V2(0x2060,""),
    LE_CONTROLLER_LE_READ_ISO_TX_SYNC(0x2061,""),
    LE_CONTROLLER_LE_SET_CIG_PARAMETERS(0x2062,""),
    LE_CONTROLLER_LE_SET_CIG_PARAMETERS_TEST(0x2063,""),
    LE_CONTROLLER_LE_CREATE_CIS(0x2064,""),
    LE_CONTROLLER_LE_REMOVE_CIG(0x2065,""),
    LE_CONTROLLER_LE_ACCEPT_CIS_REQUEST(0x2066,""),
    LE_CONTROLLER_LE_REJECT_CIS_REQUEST(0x2067,""),
    LE_CONTROLLER_LE_CREATE_BIG(0x2068,""),
    LE_CONTROLLER_LE_TERMINATE_BIG(0x206A,""),
    LE_CONTROLLER_LE_BIG_CREATE_SYNC(0x206B,""),
    LE_CONTROLLER_LE_BIG_TERMINATE_SYNC(0x206C,""),
    LE_CONTROLLER_LE_REQUEST_PEER_SCA(0x206D,""),
    LE_CONTROLLER_LE_SETUP_ISO_DATA_PATH(0x206E,""),
    LE_CONTROLLER_LE_REMOVE_ISO_DATA_PATH(0x206F,""),
    LE_CONTROLLER_LE_SET_HOST_FEATURE(0x2074,""),
    LE_CONTROLLER_LE_READ_ISO_LINK_QUALITY(0x2075,""),
    LE_CONTROLLER_LE_ENHANCED_READ_TRANSMIT_POWER_LEVEL(0x2076,""),
    LE_CONTROLLER_LE_READ_REMOTE_TRANSMIT_POWER_LEVEL(0x2077,""),
    LE_CONTROLLER_LE_SET_PATH_LOSS_REPORTING_PARAMETERS(0x2078,""),
    LE_CONTROLLER_LE_SET_PATH_LOSS_REPORTING_ENABLE(0x2079,""),
    LE_CONTROLLER_LE_SET_TRANSMIT_POWER_REPORTING_ENABLE(0x207A,""),
    VENDOR_SPECIFIC_LE_GET_VENDOR_CAPABILITIES(0xFD53,""),
    VENDOR_SPECIFIC_LE_MULTI_ADVT(0xFD54,""),
    VENDOR_SPECIFIC_LE_BATCH_SCAN(0xFD56,""),
    VENDOR_SPECIFIC_LE_ADV_FILTER(0xFD57,""),
    VENDOR_SPECIFIC_LE_ENERGY_INFO(0xFD59,""),
    VENDOR_SPECIFIC_LE_EXTENDED_SCAN_PARAMS(0xFD5A,""),
    VENDOR_SPECIFIC_CONTROLLER_DEBUG_INFO(0xFD5B,""),
    VENDOR_SPECIFIC_CONTROLLER_A2DP_OPCODE(0xFD5D,""),
    VENDOR_SPECIFIC_CONTROLLER_BQR(0xFD5E,"");

    int code;
    String description;

    CommandCode(int code, String description)
    {
        this.code = code;
        this.description = description;
    }
}
