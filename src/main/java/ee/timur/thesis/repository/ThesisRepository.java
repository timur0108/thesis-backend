package ee.timur.thesis.repository;

import ee.timur.thesis.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    @Query("select t from Thesis t left join fetch t.finalGrade")
    List<Thesis> findAllWithFinalGrade();
}
