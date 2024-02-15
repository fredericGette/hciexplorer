package fr.gette.hciexplorer.hciSpecification.event.commandComplete;

import fr.gette.hciexplorer.hciSpecification.command.CommandCode;
import fr.gette.hciexplorer.hciSpecification.command.ErrorCode;
import fr.gette.hciexplorer.hciSpecification.ioCtlHelper.IoCtlMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class ReadLocalOobDataComplete extends EventCommandComplete {
    private CommandCode commandOpCode = CommandCode.READ_LOCAL_OOB_DATA;
    private ErrorCode status;
    private BigInteger hashC;
    private BigInteger randomizerR;

    public void setHashC(IoCtlMessage data)
    {
        hashC = BigInteger.ZERO;
        for (int i=0; i<16; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.readUChar()).shiftLeft(i*8);
            hashC = hashC.add(octet);
        }
    }

    public void setRandomizerR(IoCtlMessage data)
    {
        randomizerR = BigInteger.ZERO;
        for (int i=0; i<16; i++)
        {
            BigInteger octet = BigInteger.valueOf(data.readUChar()).shiftLeft(i*8);
            randomizerR = randomizerR.add(octet);
        }
    }

}
