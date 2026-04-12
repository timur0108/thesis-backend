package ee.timur.thesis.repository;

import ee.timur.thesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmailEquals(@Param("email") String email);

    @Query("""
        select u from User u
        join SessionUserRole sur on sur.user = u
        join sur.session s
        join Thesis t on t.session = s
        where sur.user = u
          and t.session = s
          and t.id = :thesisId
          and sur.role.roleName = 'COMMITTEE_MEMBER'
    """)
    List<User> findCommitteeMembersByThesis(@Param("thesisId") Long thesisId);

    @Query("""
        select u from User u
        join SessionUserRole sur on sur.user = u
        join sur.session s
        join Thesis t on t.session = s
        where t.id = :thesisId
          and sur.role.roleName = 'HEAD_OF_COMMITTEE'
    """)
    Optional<User> findHeadOfCommitteeByThesis(@Param("thesisId") Long thesisId);

    @Query(
            "select u from User u join ThesisUserRole tur on tur.user = u where tur.thesis.id = :thesisId and tur.role.roleName = 'REVIEWER'"
    )
    Optional<User> findReviewerByThesisId(@Param("thesisId") Long thesisId);

    @Query(
            "select u from User u join ThesisUserRole tur on tur.user = u  where tur.thesis.id = :thesisId and tur.role.roleName = 'SUPERVISOR'"
    )
    Optional<User> findSupervisorByThesisId(@Param("thesisId") Long thesisId);

    @Query(
            "select u from User u join ThesisUserRole tur on tur.user = u where tur.thesis.id = :thesisId and tur.role.roleName = 'CO-SUPERVISOR'"
    )
    List<User> findCoSupervisorsByThesisId(@Param("thesisId") Long thesisId);

    @Query("""
        select sur.user
        from SessionUserRole sur
        where sur.session.id = :sessionId
          and sur.role.roleName = 'HEAD_OF_COMMITTEE'
    """)
    Optional<User> findHeadOfCommitteeBySessionId(Long sessionId);

    @Query("""
        select sur.user
        from SessionUserRole sur
        where sur.session.id = :sessionId
          and sur.role.roleName = 'COMMITTEE_MEMBER'
    """)
    List<User> findCommitteeMembersBySessionId(Long sessionId);
}
