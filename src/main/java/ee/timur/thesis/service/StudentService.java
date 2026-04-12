package ee.timur.thesis.service;

import ee.timur.thesis.dto.StudentDTO;
import ee.timur.thesis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    var dto = new StudentDTO();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setSecondName(student.getSecondName());
                    dto.setEmail(student.getEmail());

                    return dto;
                }).toList();
    }
}
