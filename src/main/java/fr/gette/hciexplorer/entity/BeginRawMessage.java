package fr.gette.hciexplorer.entity;

public interface BeginRawMessage extends RawMessage
{
	short[] getInputBuffer();
	void setInputBuffer(short[] inputBuffer);
}
