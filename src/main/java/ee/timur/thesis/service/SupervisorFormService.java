package ee.timur.thesis.service;

import ee.timur.thesis.dto.SupervisorFormDTO;
import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.mapper.SupervisorFormMapper;
import ee.timur.thesis.model.SupervisorForm;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.SupervisorFormRepository;
import ee.timur.thesis.repository.ThesisRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupervisorFormService {

    private final SupervisorFormRepository supervisorFormRepository;
    private final SupervisorFormMapper supervisorFormMapper;
    private final ThesisRepository thesisRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void saveSupervisorForm(ThesisCreateDTO dto, Thesis thesis) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);
        SupervisorForm supervisorForm = supervisorFormMapper.toEntityFromThesisCreateDTO(dto, thesis, user);
        supervisorFormRepository.save(supervisorForm);
    }

    public SupervisorFormDTO getSupervisorForm(Long thesisId) {
        return supervisorFormMapper.toDTO(
          supervisorFormRepository.findSupervisorFormByThesisId(thesisId).orElseThrow()
        );
    }

    public SupervisorFormDTO saveSupervisorForm(SupervisorFormDTO supervisorFormDTO, Long thesisId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = entityManager.getReference(User.class, userId);
        Thesis thesis = thesisRepository.getReferenceById(thesisId);
        return supervisorFormMapper.toDTO(
                supervisorFormRepository.save(supervisorFormMapper.toEntity(supervisorFormDTO, thesis, user))
        );
    }
}
