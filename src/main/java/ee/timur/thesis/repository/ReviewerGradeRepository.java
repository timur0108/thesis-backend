package ee.timur.thesis.repository;

import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.repository.common.GradeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerGradeRepository extends GradeRepository<ReviewerGrade> {

}
