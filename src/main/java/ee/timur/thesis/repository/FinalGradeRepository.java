package ee.timur.thesis.repository;

import ee.timur.thesis.model.FinalGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinalGradeRepository extends JpaRepository<FinalGrade, Long> {

    Optional<FinalGrade> findByThesisId(Long thesisId);
}
