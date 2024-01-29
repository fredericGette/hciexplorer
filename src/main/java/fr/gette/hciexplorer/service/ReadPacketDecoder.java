package fr.gette.hciexplorer.service;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.EndReadRawMessage;
import fr.gette.hciexplorer.hciSpecification.HciPacketType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadPacketDecoder {

    class Data {
        short[] data;
        int offset;

        Data(short[]data)
        {
            this.data = data;
            offset = 0;
        }

        short read()
        {
            return data[offset++];
        }
    }

    private long readULong(Data data)
    {
        long result = 0;
        result += data.read();
        result += data.read() >> 8;
        result += data.read() >> 16;
        result += data.read() >> 32;

        return result;
    }

    private short readUChar(Data data)
    {
        return data.read();
    }

    public void decode(BeginReadRawMessage begin, EndReadRawMessage end)
    {
        Data beginData = new Data(begin.getInputBuffer());
        // The message contains only one information: the type of the HCI packet.
        HciPacketType hciPacketTypeBegin = HciPacketType.get(readULong(beginData));

        Data endData = new Data(end.getOutputBuffer());
        long size = readULong(endData); // size of the HCI packet
        HciPacketType hciPacketTypeEnd = HciPacketType.get(readUChar(endData));

        switch (hciPacketTypeEnd)
        {
            case Event -> {
                short eventCode = readUChar(endData);
                short payloadLength = readUChar(endData);
            }
        }
    }
}
