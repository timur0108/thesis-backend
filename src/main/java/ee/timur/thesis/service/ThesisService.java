package ee.timur.thesis.service;

import ee.timur.thesis.dto.NewThesisDTO;
import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.mapper.ThesisMapper;
import ee.timur.thesis.mapper.UserMapper;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.ThesisUserRole;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.*;
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
    private final UserMapper userMapper;
    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    public List<ThesisDTO> getAllThesises() {
        return thesisRepository.findAllWithFinalGrade().stream().map(thesisMapper::toDTO)
                .map(thesisDTO -> {
                    thesisDTO.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesisDTO.getId()).orElseThrow()));
                    thesisDTO.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesisDTO.getId()).orElseThrow()));
                    thesisDTO.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesisDTO.getId()).orElseThrow()));
                    thesisDTO.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesisDTO.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesisDTO.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesisDTO.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());

                    return thesisDTO;
                })
                .toList();
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

        thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
        thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
        thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
        thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                .map(userMapper::toDTO)
                .toList());
        thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                .map(userMapper::toDTO)
                .toList());

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

    public List<ThesisDTO> getAllBySessionId(Long sessionId) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId).orElseThrow();
        return thesisRepository.findBySessionId(sessionId).stream()
                .map(thesisMapper::toDTO)
                .map(thesis -> {

                    List<String> thesisRoles = thesisRepository.getRolesFromThesis(thesis.getId(), userId);
                    List<String> sessionRoles = thesisRepository.getRolesFromSession(thesis.getId(), userId);

                    Set<String> allRoles = new HashSet<>();
                    allRoles.addAll(thesisRoles);
                    allRoles.addAll(sessionRoles);
                    thesis.setRoles(new ArrayList<>(allRoles));

                    thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
                    thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    return thesis;
                })
                .filter(dto -> user.getIsAdmin() || !dto.getRoles().isEmpty())
                .toList();
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
                    thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
                    thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
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
                    thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
                    thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
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
                    thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
                    thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
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
                    thesis.setReviewer(userMapper.toDTO(userRepository.findReviewerByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setSupervisor(userMapper.toDTO(userRepository.findSupervisorByThesisId(thesis.getId()).orElseThrow()));
                    thesis.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeByThesis(thesis.getId()).orElseThrow()));
                    thesis.setCommitteeMembers(userRepository.findCommitteeMembersByThesis(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    thesis.setCoSupervisors(userRepository.findCoSupervisorsByThesisId(thesis.getId()).stream()
                            .map(userMapper::toDTO)
                            .toList());
                    return thesis;
                })
                .toList();
    }

    public ThesisDTO addToSession(NewThesisDTO dto) {
        var thesis = new Thesis();
        thesis.setLevelOfStudies(dto.getLevelOfStudies());
        thesis.setCurriculum(dto.getCurriculum());
        thesis.setLanguageOfThesis(dto.getLanguageOfThesis());
        thesis.setTitleEstonian(dto.getTitleEstonian());
        thesis.setTitleEnglish(dto.getTitleEnglish());
        thesis.setVolumeEcts(dto.getVolumeEcts());
        thesis.setGradesVisible(false);
        thesis.setSession(sessionRepository.getReferenceById(dto.getSessionId()));
        thesis.setStudent(studentRepository.getReferenceById(dto.getStudentId()));

        var thesisUserRoles = new ArrayList<ThesisUserRole>();

        var reviewer = new ThesisUserRole();
        reviewer.setThesis(thesis);
        reviewer.setUser(userRepository.getReferenceById(dto.getReviewerId()));
        reviewer.setRole(roleRepository.findByRoleNameEquals("REVIEWER").orElseThrow());
        thesisUserRoles.add(reviewer);

        var supervisor = new ThesisUserRole();
        supervisor.setThesis(thesis);
        supervisor.setUser(userRepository.getReferenceById(dto.getSupervisorId()));
        supervisor.setRole(roleRepository.findByRoleNameEquals("SUPERVISOR").orElseThrow());
        thesisUserRoles.add(supervisor);

        dto.getCoSupervisorIds().forEach(coSupervisorId -> {
            var coSupervisor = new ThesisUserRole();
            coSupervisor.setThesis(thesis);
            coSupervisor.setUser(userRepository.getReferenceById(coSupervisorId));
            coSupervisor.setRole(roleRepository.findByRoleNameEquals("CO-SUPERVISOR").orElseThrow());
            thesisUserRoles.add(coSupervisor);
        });

        thesis.setThesisUserRoles(thesisUserRoles);
        return thesisMapper.toDTO(thesisRepository.save(thesis));
    }
}
