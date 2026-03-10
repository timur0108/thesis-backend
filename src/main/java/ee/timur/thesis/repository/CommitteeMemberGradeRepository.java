package ee.timur.thesis.repository;

import ee.timur.thesis.model.CommitteeMemberGrade;
import ee.timur.thesis.repository.common.GradeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeMemberGradeRepository extends GradeRepository<CommitteeMemberGrade> {

}
