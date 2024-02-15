package fr.gette.hciexplorer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

public class MissingEndReadRawMessage extends EndReadRawMessage
{
	public MissingEndReadRawMessage(Long id)
	{
		setId(id);
	}
}
