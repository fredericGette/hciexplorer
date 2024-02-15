package fr.gette.hciexplorer.repository;

import fr.gette.hciexplorer.entity.EndWriteRawMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndWriteRawMessageRepository extends JpaRepository<EndWriteRawMessage, Long>
{
    EndWriteRawMessage findFirstByOrderByTimestampDesc();
}
