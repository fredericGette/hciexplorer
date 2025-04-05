package fr.gette.hciexplorer.hciSpecification.data.l2cap.attribute;

import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AttributeDataList {
    private short length;
    List<AttributeData> list;

    public AttributeDataList(BinaryMessage data)
    {
        length = data.read1octet();
        if (length != 6
            && length !=7 )
        {
            throw new UnsupportedOperationException(String.format("Unsupported length %d", length));
        }
        list = new ArrayList<>();
        do {
            switch (length)
            {
                case 6 -> {
                    // Service declaration
                    ServiceDeclaration attributeData = new ServiceDeclaration();
                    attributeData.setAttributeHandle(data.read2octets());
                    attributeData.setEndGroupHandle(data.read2octets());
                    attributeData.setAttributeValue(GattService.type16bits(data));
                    list.add(attributeData);
                }
                case 7 -> {
                    // Characteristic declaration
                    CharacteristicDeclaration attributeData = new CharacteristicDeclaration();
                    attributeData.setAttributeHandle(data.read2octets());
                    attributeData.setAttributeValue(GattCharacteristic.type16bits(data));
                    list.add(attributeData);
                }
            }
        } while(data.hasRemaining());
    }
}
