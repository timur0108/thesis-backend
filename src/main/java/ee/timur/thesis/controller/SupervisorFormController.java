package ee.timur.thesis.controller;

import ee.timur.thesis.service.SupervisorFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/supervisor-form")
public class SupervisorFormController {

    private final SupervisorFormService supervisorFormService;

}
