package fr.gette.hciexplorer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class EndReadRawMessage extends DataRawMessage implements EndRawMessage
{
	@Id
	private Long id;

	private ZonedDateTime timestamp;

	private short[] outputBuffer;

	private Long status;

	public EndReadRawMessage()
	{
	}

	public EndReadRawMessage(UnparsedRawMessage rawMsg)
	{
		this.id = rawMsg.getId();
		this.timestamp = rawMsg.getTimestamp();
	}

	@Override
	protected short[] getData()
	{
		return getOutputBuffer();
	}
}
