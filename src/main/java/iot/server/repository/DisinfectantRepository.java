package iot.server.repository;

import iot.server.domain.Disinfectant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisinfectantRepository extends JpaRepository<Disinfectant,Long> {
}
