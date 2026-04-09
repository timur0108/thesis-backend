package ee.timur.thesis.controller;

import ee.timur.thesis.dto.SupervisorFormDTO;
import ee.timur.thesis.service.SupervisorFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/supervisor-form")
public class SupervisorFormController {

    private final SupervisorFormService supervisorFormService;

    @GetMapping("/{thesisId}")
    public SupervisorFormDTO getSupervisorForm(@PathVariable Long thesisId) {
        return supervisorFormService.getSupervisorForm(thesisId);
    }

    @PostMapping("/{thesisId}")
    public SupervisorFormDTO submitSupervisorForm(@PathVariable Long thesisId, @RequestBody SupervisorFormDTO supervisorFormDTO) {
        return supervisorFormService.saveSupervisorForm(supervisorFormDTO, thesisId);
    }
}
