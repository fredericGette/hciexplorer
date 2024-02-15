package fr.gette.hciexplorer.repository;

import fr.gette.hciexplorer.entity.EndReadRawMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndReadRawMessageRepository extends JpaRepository<EndReadRawMessage, Long>
{
    EndReadRawMessage findFirstByOrderByTimestampDesc();
}
