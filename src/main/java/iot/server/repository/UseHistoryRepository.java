package iot.server.repository;

import iot.server.domain.UseHistory;
import iot.server.model.responseDto.userHistory.UseHistoryByDateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseHistoryRepository extends JpaRepository<UseHistory,Long> {}
