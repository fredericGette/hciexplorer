package fr.gette.hciexplorer.hciSpecification.command;

import fr.gette.hciexplorer.hciSpecification.BluetoothAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PinCodeRequestReply extends Command {
    private CommandCode opCode = CommandCode.PIN_CODE_REQUEST_REPLY;
    private BluetoothAddress bdAddr;
    private short pinCodeLength;
    private short[] pinCode;

    public String getPinCodeString() {
        StringBuilder pinCodeString = new StringBuilder(pinCodeLength);
        for (int i=0; i<pinCodeLength; i++) {
            char c = (char)pinCode[i];
            pinCodeString.append(c);
        }
        return pinCodeString.toString();
    }
}
