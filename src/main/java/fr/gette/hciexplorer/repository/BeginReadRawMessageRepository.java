package fr.gette.hciexplorer.repository;

import fr.gette.hciexplorer.entity.BeginReadRawMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeginReadRawMessageRepository extends JpaRepository<BeginReadRawMessage, Long>
{
    List<BeginReadRawMessage> findByOrderByTimestampAsc();

    BeginReadRawMessage findFirstByOrderByTimestampDesc();
}
