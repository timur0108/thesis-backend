package ee.timur.thesis.controller;

import ee.timur.thesis.dto.CommitteeMemberGradeDTO;
import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/reviewer/{thesisId}")
    public ResponseEntity<ReviewerGradeDTO> getReviewerGrade(@PathVariable Long thesisId) {
        return ResponseEntity.ok(gradeService.getReviewerGrade(thesisId));
    }

    @PreAuthorize("hasAuthority('COMMITTEE_MEMBER')")
    @PostMapping("/committee-member")
    public ResponseEntity<CommitteeMemberGradeDTO> submitCommitteeMemberGrade(@RequestBody CommitteeMemberGradeDTO committeeMemberGradeDTO) {
        return new ResponseEntity<>(gradeService.saveCommitteeMemberGrade(committeeMemberGradeDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('COMMITTEE_MEMBER')")
    @GetMapping("/committee-member/other-members/{thesisId}")
    public List<CommitteeMemberGradeDTO> getAllCommitteeMemberGradesOfOtherMembers(@PathVariable Long thesisId) {
        return gradeService.getAllCommitteeGradesOfOtherMembers(thesisId);
    }

    @PreAuthorize("hasAuthority('COMMITTEE_MEMBER')")
    @GetMapping("/committee-member/own-grade/{thesisId}")
    public CommitteeMemberGradeDTO getCommitteeMemberOwnGrade(@PathVariable Long thesisId) {
        return gradeService.getCommitteeMemberGrade(thesisId);
    }

    @PreAuthorize("hasAuthority('HEAD_OF_COMMITTEE')")
    @GetMapping("/committee-member/{thesisId}/all")
    public List<CommitteeMemberGradeDTO> getAllCommitteeMemberGrades(@PathVariable Long thesisId) {
        return gradeService.getAllCommitteeMemberGrades(thesisId);
    }

    @PreAuthorize("hasAuthority('HEAD_OF_COMMITTEE')")
    @PostMapping("/make-visible/{thesisId}")
    public List<CommitteeMemberGradeDTO> makeCommitteeMemberGradesVisible(@PathVariable Long thesisId) {
        return gradeService.makeCommitteeMemberGradesVisible(thesisId);
    }

    @PreAuthorize("hasAuthority('HEAD_OF_COMMITTEE')")
    @PostMapping("/hide/{thesisId}")
    public List<CommitteeMemberGradeDTO> hideCommitteeMemberGrades(@PathVariable Long thesisId) {
        return gradeService.hideCommitteeMemberGrades(thesisId);
    }
}
