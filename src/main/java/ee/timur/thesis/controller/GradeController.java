package ee.timur.thesis.controller;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/grade")
public class GradeController {

    private final GradeService gradeService;

    @PreAuthorize("hasAuthority('REVIEWER')")
    @PostMapping("/reviewer")
    public ResponseEntity<Void> submitReviewerGrade(@RequestBody ReviewerGradeDTO reviewerGradeDTO) {
        gradeService.saveReviewerGrade(reviewerGradeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
