package iot.server.repository;

import iot.server.domain.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory,Long> {
}
