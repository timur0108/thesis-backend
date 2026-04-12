package ee.timur.thesis.repository;

import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    @Query("select t from Thesis t left join fetch t.finalGrade")
    List<Thesis> findAllWithFinalGrade();

    @Query("select t from Thesis t left join fetch t.finalGrade join t.thesisUserRoles tur join tur.role r where tur.user.id = :userId and (r.roleName = 'SUPERVISOR' or r.roleName = 'CO-SUPERVISOR')")
    List<Thesis> findSupervised(@Param("userId") Long userId);

    @Query("select t from Thesis t left join fetch t.finalGrade join t.thesisUserRoles tur join tur.role r where tur.user.id = :userId and r.roleName = 'REVIEWER'")
    List<Thesis> findAssigendReviews(@Param("userId") Long userId);

    @Query("select t from Thesis t left join fetch t.finalGrade join Session s on t.session.id = s.id join s.sessionUserRoles sur where sur.user.id = :userId and (sur.role.roleName = 'COMMITTEE_MEMBER' or sur.role.roleName = 'HEAD_OF_COMMITTEE')")
    List<Thesis> findCommitteeMemberOrHead(@Param("userId") Long userId);

    @Query("""
        select distinct tur.role.roleName
        from ThesisUserRole tur
        where tur.thesis.id = :thesisId and tur.user.id = :userId
    """)
    List<String> getRolesFromThesis(@Param("thesisId") Long thesisId, @Param("userId") Long userId);


    @Query("""
        select distinct sur.role.roleName
        from SessionUserRole sur
        join sur.session s
        join Thesis t on t.session.id = s.id
        where t.id = :thesisId and sur.user.id = :userId
    """)
    List<String> getRolesFromSession(@Param("thesisId") Long thesisId, @Param("userId") Long userId);

    @Query("""
    select distinct t
    from Thesis t
    left join t.session s
    left join s.sessionUserRoles sur
    left join t.thesisUserRoles tur
    where sur.user.id = :userId
       or tur.user.id = :userId
""")
    List<Thesis> findAllAssigned(@Param("userId") Long userId);

    @Query(
            "select t from Thesis t where t.session.id = :sessionId"
    )
    List<Thesis> findBySessionId(@Param("sessionId") Long sessionId);
}
