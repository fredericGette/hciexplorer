package fr.gette.hciexplorer.hciSpecification.helper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BinaryMessage {
    private final short[] data;
    private int offset;

    public BinaryMessage(short[] data) {
        this.data = data;
        offset = 0;
    }

    public BinaryMessage(String data) {
        String[] strings = data.split(" ");
        List<Short> shorts = Stream.of(strings).map(string -> Short.parseShort(string, 16)).toList();
        this.data = new short[shorts.size()];
        for (int i=0; i< shorts.size(); i++) {
            this.data[i] = shorts.get(i);
        }
        offset = 0;
    }


    public short read1octet() {
        return data[offset++];
    }

    public short read1signedOctet()
    {
        BigInteger unsigned = BigInteger.valueOf(read1octet());
        short signed = unsigned.shortValue();
        if (unsigned.testBit(7))
        {
            signed = BigInteger.valueOf(-128).add(unsigned.and(BigInteger.valueOf(127))).shortValue();
        }
        return signed;
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

    public String readString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++)
        {
            char c = (char) read1octet();
            sb.append(c);
        }
        return sb.toString();
    }

    public String readNullTerminatedString(int maxLength) {
        StringBuilder sb = new StringBuilder(maxLength);
        for (int i=0; i<maxLength; i++)
        {
            char c = (char) read1octet();
            if (c == 0x00)
            {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public short[] readRemaining()
    {
        short[] remainingData = Arrays.copyOfRange(data, offset, data.length);
        offset = data.length;
        return remainingData;
    }

    public int getDataLength()
    {
        if (data!= null) {
            return data.length;
        }
        return 0;
    }

    public void resetOffset()
    {
        offset = 0;
    }
}
