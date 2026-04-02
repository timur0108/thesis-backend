package ee.timur.thesis.controller;

import ee.timur.thesis.dto.SessionDTO;
import ee.timur.thesis.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
