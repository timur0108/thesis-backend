package ee.timur.thesis.service;

import ee.timur.thesis.dto.FinalGradeDTO;
import ee.timur.thesis.mapper.FinalGradeMapper;
import ee.timur.thesis.model.CommitteeMemberGrade;
import ee.timur.thesis.model.FinalGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.repository.CommitteeMemberGradeRepository;
import ee.timur.thesis.repository.FinalGradeRepository;
import ee.timur.thesis.repository.ThesisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinalGradeService {

    private final FinalGradeRepository finalGradeRepository;
    private final FinalGradeMapper finalGradeMapper;
    private final ThesisRepository thesisRepository;
    private final CommitteeMemberGradeRepository committeeMemberGradeRepository;

    public FinalGradeDTO getFinalGrade(Long thesisId) {
        return finalGradeMapper.toDTO(finalGradeRepository.findByThesisId(thesisId).orElseThrow());
    }

    public FinalGradeDTO saveFinalGrade(FinalGradeDTO finalGradeDTO) {
        Thesis thesis = thesisRepository.getReferenceById(finalGradeDTO.getThesisId());
        FinalGrade finalGrade = finalGradeRepository.save(finalGradeMapper.toEntity(finalGradeDTO, thesis));
        //validate thesis grades
        CommitteeMemberGrade committeeMemberGrade = committeeMemberGradeRepository.findGradesByThesis(thesis.getId()).getFirst();
        BigDecimal sum = committeeMemberGrade.getAppearanceScore().add(committeeMemberGrade.getPresentationScore()).add(committeeMemberGrade.getComplexityScore()).add(committeeMemberGrade.getContentScore());
        BigDecimal totalScore = sum.multiply(BigDecimal.valueOf(5));
        finalGrade.setTotalScore(totalScore);
        String letter;
        if (totalScore.compareTo(BigDecimal.valueOf(90)) > 0) letter = "A";
        else if (totalScore.compareTo(BigDecimal.valueOf(80)) > 0) letter = "B";
        else if (totalScore.compareTo(BigDecimal.valueOf(70)) > 0) letter = "C";
        else if (totalScore.compareTo(BigDecimal.valueOf(60)) > 0) letter = "D";
        else if (totalScore.compareTo(BigDecimal.valueOf(50)) > 0) letter = "E";
        else letter = "F";
        finalGrade.setLetterGrade(letter);
        return finalGradeMapper.toDTO(finalGradeRepository.save(finalGrade));
    }
}
