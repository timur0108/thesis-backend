package ee.timur.thesis.controller;

import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.service.ThesisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/thesis")
@CrossOrigin(origins = "http://localhost:4200")
public class ThesisController {

    private final ThesisService thesisService;


    @GetMapping("/all")
    List<ThesisDTO> getAll() {
        return thesisService.getAllThesises();
    }

    @GetMapping("/{id}")
    ThesisDTO getById(@PathVariable Long id) {
        return thesisService.getThesisById(id);
    }

}
