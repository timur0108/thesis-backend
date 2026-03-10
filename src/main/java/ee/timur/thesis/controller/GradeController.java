package ee.timur.thesis.controller;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/grade")
public class GradeController {

    private final GradeService gradeService;

    @PreAuthorize("hasAuthority('REVIEWER')")
    @PostMapping("/reviewer")
    public ResponseEntity<ReviewerGradeDTO> submitReviewerGrade(@RequestBody ReviewerGradeDTO reviewerGradeDTO) {
        return new ResponseEntity<>(gradeService.saveReviewerGrade(reviewerGradeDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('REVIEWER')")
    @GetMapping("/reviewer/{thesisId}")
    public ResponseEntity<ReviewerGradeDTO> getReviewerGrade(@PathVariable Long thesisId) {
        return ResponseEntity.ok(gradeService.getReviewerGrade(thesisId));
    }
}
