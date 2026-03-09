package ee.timur.thesis.service;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.mapper.ReviewerGradeMapper;
import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.repository.ReviewerGradeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final ReviewerGradeRepository reviewerGradeRepository;
    private final ReviewerGradeMapper reviewerGradeMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void saveReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        validateReviewerGrade(reviewerGradeDTO);
        Thesis thesis = entityManager.getReference(Thesis.class, reviewerGradeDTO.getThesisId());
        ReviewerGrade reviewerGrade = reviewerGradeMapper.toEntity(reviewerGradeDTO, thesis);
        reviewerGradeRepository.save(reviewerGrade);
    }

    private void validateReviewerGrade(ReviewerGradeDTO reviewerGradeDTO) {
        return;
    }

}
