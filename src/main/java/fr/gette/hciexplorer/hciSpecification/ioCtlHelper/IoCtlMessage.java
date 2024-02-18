package fr.gette.hciexplorer.hciSpecification.ioCtlHelper;

public class IoCtlMessage {
    short[] data;
    int offset;

    public IoCtlMessage(short[] data) {
        this.data = data;
        offset = 0;
    }

    public short read1octet() {
        return data[offset++];
    }

    public int read2octets() {
        int result = 0;
        result += read1octet();
        result += read1octet() << 8;
        return result;
    }

    public long read3octets() {
        long result = 0;
        result += read1octet();
        result += read1octet() << 8;
        result += read1octet() << 16;

        return result;
    }

    public long read4octets() {
        long result = 0;
        result += read1octet();
        result += read1octet() << 8;
        result += read1octet() << 16;
        result += read1octet() << 24;

        return result;
    }
}
