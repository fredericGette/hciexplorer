package fr.gette.hciexplorer.entity;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Scanner;

public abstract class DataRawMessage
{
	@Transient
	private int dataOffset = 0;
	public void addData(String dataString)
	{
		short[] data = getData();
		Scanner dataScanner = new Scanner(dataString);
		while (dataScanner.hasNextShort(16)) {
			// We read "byte" values as "short" values to avoid overflow problems
			// see https://stackoverflow.com/questions/48426307/converting-a-string-of-hexadecimal-valiues-into-an-array-of-bytes-using-scanner
			short dataByte = dataScanner.nextShort(16);
			data[dataOffset++] = dataByte;
		}
	}

	abstract protected short[] getData();

	public boolean parsingFinished()
	{
		if (null == getData())
		{
			return false;
		}

		return getData().length == dataOffset;
	}
}
