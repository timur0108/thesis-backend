package ee.timur.thesis.service;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.mapper.ReviewerGradeMapper;
import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.ReviewerGradeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final ReviewerGradeRepository reviewerGradeRepository;
    private final ReviewerGradeMapper reviewerGradeMapper;

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

    @PreAuthorize("hasAuthority('REVIEWER')")
    public ReviewerGradeDTO getReviewerGrade(Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reviewerGradeRepository
                .findGradeByThesisAndUser(thesisId, userId)
                .map(reviewerGradeMapper::toDTO)
                .orElse(null);
    }

    private void validateReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        return;
    }



}
