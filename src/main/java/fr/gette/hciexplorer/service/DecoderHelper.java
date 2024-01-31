package fr.gette.hciexplorer.service;

public class DecoderHelper {

    private DecoderHelper()
    {

    }

    static long readULong(IoMessage data)
    {
        long result = 0;
        result += data.read();
        result += data.read() << 8;
        result += data.read() << 16;
        result += data.read() << 24;

        return result;
    }

    static short readUChar(IoMessage data)
    {
        return data.read();
    }

    public static class IoMessage {
        short[] data;
        int offset;

        IoMessage(short[]data)
        {
            this.data = data;
            offset = 0;
        }

        short read()
        {
            return data[offset++];
        }
    }
}
