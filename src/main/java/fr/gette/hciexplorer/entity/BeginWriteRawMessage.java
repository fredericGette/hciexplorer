package fr.gette.hciexplorer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class BeginWriteRawMessage extends DataRawMessage implements BeginRawMessage
{
	@Id
	private Long id;

	private ZonedDateTime timestamp;

	private byte[] inputBuffer;

	public BeginWriteRawMessage()
	{
	}

	public BeginWriteRawMessage(UnparsedRawMessage rawMsg)
	{
		this.id = rawMsg.getId();
		this.timestamp = rawMsg.getTimestamp();
	}

	@Override
	protected byte[] getData()
	{
		return getInputBuffer();
	}
}
