package ee.timur.thesis.controller;

import ee.timur.thesis.dto.UserDTO;
import ee.timur.thesis.model.User;
import ee.timur.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/committee-members/unsubmitted/{thesisId}")
    public List<UserDTO> getUnsubmittedCommitteeMembers(@PathVariable Long thesisId) {
        return userService.getUnsubmittedCommitteeMembers(thesisId);
    }
}
