package ee.timur.thesis.repository.common;

import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.common.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GradeRepository<T extends Grade> extends JpaRepository<T, Long> {

    @Query("select grade from #{#entityName} grade where grade.thesis.id = :thesisId")
    List<T> findGradesByThesis(@Param("thesisId") Long thesisId);

    @Query("select grade from #{#entityName} grade where grade.thesis.id = :thesisId and grade.user.id = :userId")
    Optional<T> findGradeByThesisAndUser(@Param("thesisId") Long thesisId, @Param("userId") Long userId);
}
