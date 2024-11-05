package fr.gette.hciexplorer.hciSpecification.data.l2cap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleDataFrame extends Frame {
    private short[] data;

    public String getDataString() {
        StringBuilder valueHexString = new StringBuilder();
        StringBuilder valueCharString = new StringBuilder();
        for (int i =0; i<data.length; i++) {
            short value = data[i];
            valueHexString.append(String.format("%02X ", value));
            if (value > 31 && value < 127) {
                valueCharString.append((char)value);
            } else {
                valueCharString.append('.');
            }
        }
        valueHexString.append(valueCharString);
        return valueHexString.toString();
    }
}
