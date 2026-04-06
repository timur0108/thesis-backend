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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ThesisDTO thesis =
                thesisMapper.toDTO(thesisRepository.findById(id).orElseThrow());
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
        List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

        Set<String> allRoles = new HashSet<>();
        allRoles.addAll(thesisRoles);
        allRoles.addAll(sessionRoles);

        thesis.setRoles(new ArrayList<>(allRoles));
        return thesis;
    }

    @Transactional
    public void createThesis(ThesisCreateDTO dto) {
        Thesis thesis = thesisMapper.toEntity(dto);
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId).orElseThrow();

        thesis = thesisRepository.save(thesis);
        supervisorFormService.saveSupervisorForm(dto, thesis);
    }

    public List<ThesisDTO> getAllAssigned() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thesisRepository.findAllAssigned(userId).stream()
                .map(thesisMapper::toDTO)
                .map(thesis -> {
                    List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
                    List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

                    Set<String> allRoles = new HashSet<>();
                    allRoles.addAll(thesisRoles);
                    allRoles.addAll(sessionRoles);

                    thesis.setRoles(new ArrayList<>(allRoles));

                    return thesis;
                })
                .toList();
    }

    public List<ThesisDTO> getSupervised() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thesisRepository.findSupervised(userId).stream()
                .map(thesisMapper::toDTO)
                .map(thesis -> {
                    List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
                    List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

                    Set<String> allRoles = new HashSet<>();
                    allRoles.addAll(thesisRoles);
                    allRoles.addAll(sessionRoles);

                    thesis.setRoles(new ArrayList<>(allRoles));

                    return thesis;
                })
                .toList();
    }

    public List<ThesisDTO> getAssignedReviews() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thesisRepository.findAssigendReviews(userId).stream()
                .map(thesisMapper::toDTO)
                .map(thesis -> {
                    List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
                    List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

                    Set<String> allRoles = new HashSet<>();
                    allRoles.addAll(thesisRoles);
                    allRoles.addAll(sessionRoles);

                    thesis.setRoles(new ArrayList<>(allRoles));

                    return thesis;
                })
                .toList();
    }

    public List<ThesisDTO> getCommittee() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thesisRepository.findCommitteeMemberOrHead(userId).stream()
                .map(thesisMapper::toDTO)
                .map(thesis -> {
                    List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
                    List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

                    Set<String> allRoles = new HashSet<>();
                    allRoles.addAll(thesisRoles);
                    allRoles.addAll(sessionRoles);

                    thesis.setRoles(new ArrayList<>(allRoles));

                    return thesis;
                })
                .toList();
    }
}
