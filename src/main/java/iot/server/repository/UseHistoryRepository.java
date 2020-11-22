package iot.server.repository;

import iot.server.domain.UseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseHistoryRepository extends JpaRepository<UseHistory,Long> {
}
