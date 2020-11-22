package iot.server.repository;

import iot.server.domain.Sterilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SterilizerRepository extends JpaRepository<Sterilizer,Long> {
}
