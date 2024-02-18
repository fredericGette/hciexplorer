package fr.gette.hciexplorer.repository;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import fr.gette.hciexplorer.entity.BeginWriteRawMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeginWriteRawMessageRepository extends JpaRepository<BeginWriteRawMessage, Long>
{
    BeginWriteRawMessage findFirstByOrderByTimestampDesc();

    List<BeginWriteRawMessage> findByOrderByTimestampAsc();
}
