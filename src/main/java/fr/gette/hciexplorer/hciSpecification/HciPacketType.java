package fr.gette.hciexplorer.hciSpecification;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum HciPacketType {
    Command(0x01),
    AclData(0x02),
    Event(0x04);

    private int value;

    HciPacketType(int value)
    {
        this.value = value;
    }

    public static HciPacketType get(long value)
    {
        return Arrays.stream(values()).filter(t -> t.value == value).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
