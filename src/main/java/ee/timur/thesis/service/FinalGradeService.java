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
        int totalScore = (committeeMemberGrade.getPresentationScore() +
                committeeMemberGrade.getAppearanceScore() + committeeMemberGrade.getComplexityScore() +
                committeeMemberGrade.getContentScore()) * 5;
        finalGrade.setTotalScore(totalScore);
        String letter;
        if (totalScore > 90) letter = "A";
        else if (totalScore > 80) letter = "B";
        else if (totalScore > 70) letter = "C";
        else if (totalScore > 60) letter = "D";
        else if (totalScore > 50) letter = "E";
        else letter = "F";
        finalGrade.setLetterGrade(letter);
        return finalGradeMapper.toDTO(finalGradeRepository.save(finalGrade));
    }
}
