package ee.timur.thesis.controller;

import ee.timur.thesis.dto.StudentDTO;
import ee.timur.thesis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        return studentService.findAll();
    }
}
