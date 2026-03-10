package ee.timur.thesis.repository;

import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.repository.common.GradeRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewerGradeRepository extends GradeRepository<ReviewerGrade> {

    @Query("select grade from ReviewerGrade grade where grade.thesis.id = :thesisId")
    Optional<ReviewerGrade> findReviewerGradeByThesisId(@Param("thesisId") Long thesisId);
}
