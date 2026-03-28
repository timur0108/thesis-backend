package ee.timur.thesis.repository;

import ee.timur.thesis.model.SupervisorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorFormRepository extends JpaRepository<SupervisorForm, Long> {

    Optional<SupervisorForm> findSupervisorFormByThesisId(Long thesisId);
}
