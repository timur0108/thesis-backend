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
import ee.timur.thesis.repository.ThesisRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final ReviewerGradeRepository reviewerGradeRepository;
    private final CommitteeMemberGradeRepository committeeMemberGradeRepository;
    private final ReviewerGradeMapper reviewerGradeMapper;
    private final CommitteeMemberGradeMapper committeeMemberGradeMapper;
    private final ThesisRepository thesisRepository;

    @PersistenceContext
    private final EntityManager entityManager;


    @Transactional
    public ReviewerGradeDTO saveReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        validateReviewerGrade(reviewerGradeDTO);
        Thesis thesis = entityManager.getReference(Thesis.class, reviewerGradeDTO.getThesisId());

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);

        ReviewerGrade reviewerGrade = reviewerGradeMapper.toEntity(reviewerGradeDTO, thesis, user);
        return reviewerGradeMapper.toDTO(reviewerGradeRepository.save(reviewerGrade));
    }


    @Transactional
    public CommitteeMemberGradeDTO updateCommitteeMemberGrade(CommitteeMemberGradeDTO dto) {
        CommitteeMemberGrade existingGrade =
                committeeMemberGradeRepository.findById(dto.getId()).orElseThrow();


        existingGrade.setAppearanceScore(dto.getAppearanceScore());
        existingGrade.setPresentationScore(dto.getPresentationScore());
        existingGrade.setComplexityScore(dto.getComplexityScore());
        existingGrade.setContentScore(dto.getContentScore());

        return committeeMemberGradeMapper.toDTO(committeeMemberGradeRepository.save(existingGrade));
    }

    public ReviewerGradeDTO getReviewerGrade(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reviewerGradeRepository
                .findReviewerGradeByThesisId(thesisId)
                .map(reviewerGradeMapper::toDTO)
                .orElse(null);
    }


    @Transactional
    public CommitteeMemberGradeDTO saveCommitteeMemberGrade(CommitteeMemberGradeDTO committeeMemberGradeDTO) {
        validateCommitteeMemberGrade(committeeMemberGradeDTO);

        Thesis thesis = entityManager.getReference(Thesis.class, committeeMemberGradeDTO.getThesisId());
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);

        CommitteeMemberGrade committeeMemberGrade = committeeMemberGradeMapper.toEntity(committeeMemberGradeDTO, thesis, user);
        return committeeMemberGradeMapper.toDTO(committeeMemberGradeRepository.save(committeeMemberGrade));
    }


    public List<CommitteeMemberGradeDTO> getAllCommitteeMemberGrades(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isHead = thesisRepository.getRolesFromSession(thesisId, userId).stream()
                .anyMatch(a -> a.equals("HEAD_OF_COMMITTEE"));

        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow();

        return isHead || thesis.getGradesVisible()? committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .map(committeeMemberGradeMapper::toDTO)
                .toList() : Collections.emptyList();
    }


    public List<CommitteeMemberGradeDTO> getAllCommitteeGradesOfOtherMembers(Long thesisId) {

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isHead = thesisRepository.getRolesFromSession(thesisId, userId).stream()
                .anyMatch(a -> a.equals("HEAD_OF_COMMITTEE"));

        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow();
        System.out.println(isHead + " !!!! " + thesis.getGradesVisible());
        return isHead || thesis.getGradesVisible()? committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList() : Collections.emptyList();

//        return committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
//                .filter(grade -> isHead || grade.getVisibleToOthers())
//                .filter(grade -> !grade.getUser().getId().equals(userId))
//                .map(committeeMemberGradeMapper::toDTO)
//                .toList();
    }

    public CommitteeMemberGradeDTO getCommitteeMemberGrade(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository
                .findGradeByThesisAndUser(thesisId, userId)
                .map(committeeMemberGradeMapper::toDTO)
                .orElse(null);
    }


    @Transactional
    public List<CommitteeMemberGradeDTO> makeCommitteeMemberGradesVisible(Long thesisId) {

        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow();
        thesis.setGradesVisible(true);
        thesis = thesisRepository.save(thesis);
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList();

//        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return committeeMemberGradeRepository.saveAll(
//                committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
//                        .map(grade -> {
//                            grade.setVisibleToOthers(true);
//                            return grade;
//                        })
//                        .toList()
//        ).stream()
//                .filter(grade -> !grade.getUser().getId().equals(userId))
//                .map(committeeMemberGradeMapper::toDTO)
//                .toList();
    }


    @Transactional
    public List<CommitteeMemberGradeDTO> hideCommitteeMemberGrades(Long thesisId) {

        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow();
        thesis.setGradesVisible(false);
        thesis = thesisRepository.save(thesis);
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
                .filter(grade -> !grade.getUser().getId().equals(userId))
                .map(committeeMemberGradeMapper::toDTO)
                .toList();
//        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return committeeMemberGradeRepository.saveAll(
//                        committeeMemberGradeRepository.findGradesByThesis(thesisId).stream()
//                                .map(grade -> {
//                                    grade.setVisibleToOthers(false);
//                                    return grade;
//                                })
//                                .toList()
//                ).stream()
//                .filter(grade -> !grade.getUser().getId().equals(userId))
//                .map(committeeMemberGradeMapper::toDTO)
//                .toList();
    }

    private void validateReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        return;
    }

    private void validateCommitteeMemberGrade(CommitteeMemberGradeDTO committeeMemberGradeDTO) {
        return;
    }

    public Boolean areGradesVisible(Long thesisId) {
        return thesisRepository.findById(thesisId).orElseThrow().getGradesVisible();
    }
}
