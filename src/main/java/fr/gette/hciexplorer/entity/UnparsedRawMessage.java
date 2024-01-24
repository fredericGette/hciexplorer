package fr.gette.hciexplorer.entity;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UnparsedRawMessage implements RawMessage
{
	private Long Id;

	private ZonedDateTime timestamp;
}
