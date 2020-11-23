package iot.server.repository;

import iot.server.domain.Sterilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SterilizerRepository extends JpaRepository<Sterilizer,Long> {

    Optional<Sterilizer> findBySerialNumber(String serialNumber);
}
