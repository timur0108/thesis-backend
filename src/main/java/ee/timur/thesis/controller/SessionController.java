package ee.timur.thesis.controller;

import ee.timur.thesis.dto.SessionCreateDTO;
import ee.timur.thesis.dto.SessionDTO;
import ee.timur.thesis.dto.SessionFullDTO;
import ee.timur.thesis.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/all")
    public List<SessionDTO> getAll() {
        return sessionService.getAll();
    }

    @PostMapping()
    public SessionDTO createSession(@RequestBody SessionCreateDTO sessionDTO) {
        return sessionService.createSession(sessionDTO);
    }

    @GetMapping("/with-theses/all")
    public List<SessionFullDTO> getAllSessionsWithTheses() {
        return sessionService.getAllWithTheses();
    }

//    @GetMapping("/assigned")
//    public List<SessionFullDTO> getAllAssignedSessions() {
//        return sessionService.getAllAssigned();
//    }

}
