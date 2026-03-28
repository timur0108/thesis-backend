package ee.timur.thesis.controller;

import ee.timur.thesis.dto.FinalGradeDTO;
import ee.timur.thesis.service.FinalGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/final-grade")
public class FinalGradeController {

    private final FinalGradeService finalGradeService;

    @PostMapping
    public FinalGradeDTO submitFinalGrade(@RequestBody FinalGradeDTO dto) {
        return finalGradeService.saveFinalGrade(dto);
    }

    @GetMapping("/{thesisId}")
    public FinalGradeDTO getFinalGrade(@PathVariable Long thesisId) {
        return finalGradeService.getFinalGrade(thesisId);
    }
}
