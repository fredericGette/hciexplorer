package fr.gette.hciexplorer.entity;

public interface EndRawMessage extends RawMessage
{
	short[] getOutputBuffer();
	void setOutputBuffer(short[] outputBuffer);

	Long getStatus();

	void setStatus(Long status);
}
