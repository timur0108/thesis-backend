package ee.timur.thesis.service;

import ee.timur.thesis.dto.CommitteeMemberGradeDTO;
import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.mapper.CommitteeMemberGradeMapper;
import ee.timur.thesis.mapper.ReviewerGradeMapper;
import ee.timur.thesis.model.CommitteeMemberGrade;
import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.CommitteeMemberGradeRepository;
import ee.timur.thesis.repository.ReviewerGradeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final ReviewerGradeRepository reviewerGradeRepository;
    private final CommitteeMemberGradeRepository committeeMemberGradeRepository;
    private final ReviewerGradeMapper reviewerGradeMapper;
    private final CommitteeMemberGradeMapper committeeMemberGradeMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    @PreAuthorize("hasAuthority('REVIEWER')")
    @Transactional
    public ReviewerGradeDTO saveReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        validateReviewerGrade(reviewerGradeDTO);
        Thesis thesis = entityManager.getReference(Thesis.class, reviewerGradeDTO.getThesisId());

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);

        ReviewerGrade reviewerGrade = reviewerGradeMapper.toEntity(reviewerGradeDTO, thesis, user);
        return reviewerGradeMapper.toDTO(reviewerGradeRepository.save(reviewerGrade));
    }


    public ReviewerGradeDTO getReviewerGrade(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reviewerGradeRepository
                .findReviewerGradeByThesisId(thesisId)
                .map(reviewerGradeMapper::toDTO)
                .orElse(null);
    }

    @PreAuthorize("hasAnyAuthority('COMMITTEE_MEMBER', 'HEAD_OF_COMMITTEE')")
    @Transactional
    public CommitteeMemberGradeDTO saveCommitteeMemberGrade(CommitteeMemberGradeDTO committeeMemberGradeDTO) {
        validateCommitteeMemberGrade(committeeMemberGradeDTO);

        Thesis thesis = entityManager.getReference(Thesis.class, committeeMemberGradeDTO.getThesisId());
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);

        CommitteeMemberGrade committeeMemberGrade = committeeMemberGradeMapper.toEntity(committeeMemberGradeDTO, thesis, user);
        return committeeMemberGradeMapper.toDTO(committeeMemberGradeRepository.save(committeeMemberGrade));
    }

    @PreAuthorize("hasAnyAuthority('HEAD_OF_COMMITTEE', 'REVIEWER', 'SUPERVISOR')")
    public List<CommitteeMemberGradeDTO> getAllCommitteeMemberGrades(Long thesisId) {
        return committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .map(committeeMemberGradeMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasAnyAuthority('COMMITTEE_MEMBER', 'HEAD_OF_COMMITTEE')")
    public List<CommitteeMemberGradeDTO> getAllCommitteeGradesOfOtherMembers(Long thesisId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isHead = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("HEAD_OF_COMMITTEE"));

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .filter(grade -> isHead || grade.getVisibleToOthers())
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList();
    }

    public CommitteeMemberGradeDTO getCommitteeMemberGrade(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository
                .findGradeByThesisAndUser(thesisId, userId)
                .map(committeeMemberGradeMapper::toDTO)
                .orElse(null);
    }

    @PreAuthorize("hasAuthority('HEAD_OF_COMMITTEE')")
    @Transactional
    public List<CommitteeMemberGradeDTO> makeCommitteeMemberGradesVisible(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository.saveAll(
                committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                        .map(grade -> {
                            grade.setVisibleToOthers(true);
                            return grade;
                        })
                        .toList()
        ).stream()
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasAuthority('HEAD_OF_COMMITTEE')")
    @Transactional
    public List<CommitteeMemberGradeDTO> hideCommitteeMemberGrades(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository.saveAll(
                        committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                                .map(grade -> {
                                    grade.setVisibleToOthers(false);
                                    return grade;
                                })
                                .toList()
                ).stream()
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList();
    }

    private void validateReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        return;
    }

    private void validateCommitteeMemberGrade(CommitteeMemberGradeDTO committeeMemberGradeDTO) {
        return;
    }

}
