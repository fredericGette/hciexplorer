package fr.gette.hciexplorer.hciSpecification;

import fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse.EirCompleteLocalName;
import fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse.EirDataType;
import fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse.EirTxPowerLevel;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse.EirData;

import java.util.ArrayList;
import java.util.List;

public class ExtendedInquiryResponse {

    List<EirData> eirData;

    public ExtendedInquiryResponse(IoCtlMessage data)
    {
        eirData = new ArrayList<>();

        short eirDataStructureLength;
        do {
            eirDataStructureLength = data.read1octet();
            if (eirDataStructureLength > 0)
            {
                EirDataType eirDataType = EirDataType.get(data.read1octet());
                switch (eirDataType)
                {
                    case COMPLETE_LOCAL_NAME -> {
                        EirCompleteLocalName eir = new EirCompleteLocalName();
                        eir.setCompleteLocalName(data.readString(eirDataStructureLength-1));
                        eirData.add(eir);
                    }
                    case TX_POWER_LEVEL -> {
                        EirTxPowerLevel eir = new EirTxPowerLevel();
                        eir.setTxPowerLevel(data.read1signedOctet());
                        eirData.add(eir);
                    }
                }
            }
        } while(eirDataStructureLength > 0);
    }
}
