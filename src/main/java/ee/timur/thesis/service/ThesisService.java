package ee.timur.thesis.service;

import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.mapper.ThesisMapper;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.ThesisRepository;
import ee.timur.thesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThesisService {

    private final ThesisRepository thesisRepository;
    private final ThesisMapper thesisMapper;
    private final SupervisorFormService supervisorFormService;
    private final UserRepository userRepository;

    public List<ThesisDTO> getAllThesises() {
        return thesisRepository.findAllWithFinalGrade().stream().map(thesisMapper::toDTO).toList();
    }

    public ThesisDTO getThesisById(Long id) {
        return thesisMapper.toDTO(
                thesisRepository.findById(id).orElseThrow()
        );
    }

    @Transactional
    public void createThesis(ThesisCreateDTO dto) {
        Thesis thesis = thesisMapper.toEntity(dto);
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId).orElseThrow();
        thesis.setSupervisorName(user.getName() + " " + user.getSecondName());
        thesis = thesisRepository.save(thesis);
        supervisorFormService.saveSupervisorForm(dto, thesis);
    }
}
