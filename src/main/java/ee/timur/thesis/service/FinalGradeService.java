package ee.timur.thesis.service;

import ee.timur.thesis.dto.FinalGradeDTO;
import ee.timur.thesis.mapper.FinalGradeMapper;
import ee.timur.thesis.model.Thesis;
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

    public FinalGradeDTO getFinalGrade(Long thesisId) {
        return finalGradeMapper.toDTO(finalGradeRepository.findByThesisId(thesisId).orElseThrow());
    }

    public FinalGradeDTO saveFinalGrade(FinalGradeDTO finalGradeDTO) {
        Thesis thesis = thesisRepository.getReferenceById(finalGradeDTO.getThesisId());
        return finalGradeMapper.toDTO(finalGradeRepository.save(finalGradeMapper.toEntity(finalGradeDTO, thesis)));
    }
}
