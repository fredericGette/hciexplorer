package fr.gette.hciexplorer.entity;

public interface EndRawMessage extends RawMessage
{
	Long STATUS_SUCCESS = 0L;

	short[] getOutputBuffer();
	void setOutputBuffer(short[] outputBuffer);

	Long getStatus();

	void setStatus(Long status);
}
