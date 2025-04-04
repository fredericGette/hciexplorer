package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.advertisingData.*;
import fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse.EirCompleteLocalName;
import fr.gette.hciexplorer.hciSpecification.helper.BinaryMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
public class AdvertisingOrScanResponseData {

    private short advertisingDataLength;
    private List<AdvertisingData> advertisingData;

    public AdvertisingOrScanResponseData(BinaryMessage data)
    {
        int nbOctetsRead = 0;
        advertisingDataLength = data.read1octet(); nbOctetsRead++;
        advertisingData = new ArrayList<>();
        short advertisingDataStructureLength;
        do {
            advertisingDataStructureLength = data.read1octet(); nbOctetsRead++;
            if (advertisingDataStructureLength > 0) {
                AdvertisingDataType advertisingDataType = AdvertisingDataType.get(data.read1octet()); nbOctetsRead++;
                switch (advertisingDataType)
                {
                    case COMPLETE_LOCAL_NAME -> {
                        AdCompleteLocalName ad = new AdCompleteLocalName();
                        ad.setCompleteLocalName(data.readString(advertisingDataStructureLength-1)); nbOctetsRead+=advertisingDataStructureLength-1;
                        advertisingData.add(ad);
                    }
                    case APPEARANCE -> {
                        AdAppearance ad = new AdAppearance();
                        ad.setAppearance(data.read2octets());
                        advertisingData.add(ad);
                    }
                    case FLAGS -> {
                        AdFlags ad = new AdFlags();
                        ad.setFlags(data.read1octet());
                        advertisingData.add(ad);
                    }
                    case COMPLETE_LIST_OF_16_BIT_UUIDS -> {
                        AdCompleteListOf16BitUuids ad = new AdCompleteListOf16BitUuids();
                        ad.setUuid(data.read2octets());
                        advertisingData.add(ad);
                    }
                    default ->  throw new NoSuchElementException(String.format("%s",advertisingDataType));
                }
            }
        } while(advertisingDataStructureLength > 0 || nbOctetsRead < advertisingDataLength);
    }
}
