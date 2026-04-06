package ee.timur.thesis.controller;

import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.service.ThesisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/thesis")
public class ThesisController {

    private final ThesisService thesisService;


    @GetMapping("/all")
    List<ThesisDTO> getAll() {
        return thesisService.getAllThesises();
    }

    @GetMapping("/all/assigned")
    List<ThesisDTO> getAllAssigned() {
        return thesisService.getAllAssigned();
    }

    @GetMapping("/{id}")
    ThesisDTO getById(@PathVariable Long id) {
        return thesisService.getThesisById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createThesis(@RequestBody ThesisCreateDTO dto) {
        thesisService.createThesis(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/supervised")
    public List<ThesisDTO> getSupervisedThesises() {
        return thesisService.getSupervised();
    }

    @GetMapping("/review")
    public List<ThesisDTO> getAssignedReviews() {
        return thesisService.getAssignedReviews();
    }

    @GetMapping("/committee")
    public List<ThesisDTO> getCommittee() {
        return thesisService.getCommittee();
    }
}
