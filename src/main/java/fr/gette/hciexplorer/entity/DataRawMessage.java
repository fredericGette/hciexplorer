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
		byte[] data = getData();
		Scanner dataScanner = new Scanner(dataString);
		while (dataScanner.hasNextByte(16)) {
			byte dataByte = dataScanner.nextByte(16);
			data[dataOffset++] = dataByte;
		}
	}

	abstract protected byte[] getData();

	public boolean parsingFinished()
	{
		if (null == getData())
		{
			return false;
		}

		return getData().length == dataOffset;
	}
}
