package ee.timur.thesis.repository;

import ee.timur.thesis.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("""
    select distinct s
    from Session s
    left join s.sessionUserRoles sur
    left join s.theses t
    left join t.thesisUserRoles tur
    where sur.user.id = :userId
       or tur.user.id = :userId
""")
    List<Session> findSessionsWithUserRoles(@Param("userId") Long userId);
}
