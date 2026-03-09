package ee.timur.thesis.repository.common;

import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.common.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GradeRepository<T extends Grade> extends JpaRepository<T, Long> {

    @Query("select grade from #{#entityName} grade where grade.thesis = :thesis")
    List<T> findGradesByThesis(Thesis thesis);
}
