package fr.gette.hciexplorer.entity;

public interface EndRawMessage extends RawMessage
{
	byte[] getOutputBuffer();
	void setOutputBuffer(byte[] outputBuffer);

	Long getStatus();

	void setStatus(Long status);
}
