package fr.gette.hciexplorer.entity;

public interface BeginRawMessage extends RawMessage
{
	byte[] getInputBuffer();
	void setInputBuffer(byte[] inputBuffer);
}
