package fr.gette.hciexplorer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class BeginWriteRawMessage extends DataRawMessage implements BeginRawMessage
{
	@Id
	private Long id;

	private ZonedDateTime timestamp;

	private short[] inputBuffer;

	public BeginWriteRawMessage()
	{
	}

	public BeginWriteRawMessage(UnparsedRawMessage rawMsg)
	{
		this.id = rawMsg.getId();
		this.timestamp = rawMsg.getTimestamp();
	}

	@Override
	protected short[] getData()
	{
		return getInputBuffer();
	}
}
