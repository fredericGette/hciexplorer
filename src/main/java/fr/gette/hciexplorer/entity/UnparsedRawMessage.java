package fr.gette.hciexplorer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UnparsedRawMessage implements RawMessage
{
	private Long Id;

	private ZonedDateTime timestamp;
}
