package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.advertisingData.*;
import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
public class AttributeDataList {
    private short length;
    List<AttributeData> list;

    public AttributeDataList(BinaryMessage data)
    {
        length = data.read1octet();
        if (length != 6)
        {
            throw new UnsupportedOperationException(String.format("Unsupported length %d", length));
        }
        list = new ArrayList<>();
        do {
            AttributeData attributeData = new AttributeData();
            attributeData.setAttributeHandle(data.read2octets());
            attributeData.setEndGroupHandle(data.read2octets());
            attributeData.setAttributeValue(GattService.type16bits(data.read2octets()));
            list.add(attributeData);
        } while(data.hasRemaining());
    }
}
