package fr.gette.hciexplorer.hciSpecification.ioCtlHelper;

public class IoCtlMessage {
    short[] data;
    int offset;

    public IoCtlMessage(short[] data) {
        this.data = data;
        offset = 0;
    }

    public short readUChar() {
        return data[offset++];
    }

    public int readUShort() {
        int result = 0;
        result += readUChar();
        result += readUChar() << 8;
        return result;
    }

    public long readULong() {
        long result = 0;
        result += readUChar();
        result += readUChar() << 8;
        result += readUChar() << 16;
        result += readUChar() << 24;

        return result;
    }
}
