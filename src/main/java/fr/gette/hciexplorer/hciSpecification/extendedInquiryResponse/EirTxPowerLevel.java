package fr.gette.hciexplorer.hciSpecification.extendedInquiryResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EirTxPowerLevel implements EirData {
    private EirDataType eirDataType = EirDataType.TX_POWER_LEVEL;
    private short txPowerLevel;
}
