package ee.timur.thesis.service;

import ee.timur.thesis.dto.SessionCreateDTO;
import ee.timur.thesis.dto.SessionDTO;
import ee.timur.thesis.dto.SessionFullDTO;
import ee.timur.thesis.dto.SessionWithThesisesDTO;
import ee.timur.thesis.mapper.SessionMapper;
import ee.timur.thesis.mapper.ThesisMapper;
import ee.timur.thesis.mapper.UserMapper;
import ee.timur.thesis.model.Session;
import ee.timur.thesis.model.SessionUserRole;
import ee.timur.thesis.repository.RoleRepository;
import ee.timur.thesis.repository.SessionRepository;
import ee.timur.thesis.repository.ThesisRepository;
import ee.timur.thesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ThesisRepository thesisRepository;
    private final ThesisMapper thesisMapper;
    private final UserMapper userMapper;
    private final ThesisService thesisService;

    public List<SessionDTO> getAll() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDTO)
                .toList();
    }

    @Transactional
    public SessionDTO createSession(SessionCreateDTO dto) {
        Session session = new Session();
        session.setStartDate(dto.getStartDate());
        session.setEndDate(dto.getEndDate());
        List<SessionUserRole> sessionUserRoles = dto.getCommitteeMemberIds().stream()
                .map(committeeMemberId -> {
                    var sessionUserRole = new SessionUserRole();
                    sessionUserRole.setUser(userRepository.findById(committeeMemberId).orElseThrow());
                    sessionUserRole.setSession(session);
                    sessionUserRole.setRole(roleRepository.findByRoleNameEquals("COMMITTEE_MEMBER").orElseThrow());
                    return sessionUserRole;
                }).collect(Collectors.toList());
        var head = new SessionUserRole();
        head.setUser(userRepository.findById(dto.getHeadOfCommitteeId()).orElseThrow());
        head.setSession(session);
        head.setRole(roleRepository.findByRoleNameEquals("HEAD_OF_COMMITTEE").orElseThrow());

        sessionUserRoles.add(head);

        session.setSessionUserRoles(sessionUserRoles);
        return sessionMapper.toDTO(sessionRepository.save(session));
    }

    public List<SessionFullDTO> getAllWithTheses() {
        return sessionRepository.findAll().stream()
                .map(session -> {
                    var dto = new SessionFullDTO();
                    dto.setId(session.getId());
                    dto.setStartDate(session.getStartDate());
                    dto.setEndDate(session.getEndDate());
                    dto.setTheses(thesisService.getAllBySessionId(session.getId()));
                    dto.setHeadOfCommittee(userMapper.toDTO(userRepository.findHeadOfCommitteeBySessionId(session.getId()).orElseThrow()));
                    dto.setCommitteeMembers(userRepository.findCommitteeMembersBySessionId(session.getId()).stream().map(userMapper::toDTO).toList());
                    return dto;
                }).toList();
    }

//    public List<SessionFullDTO> getAllAssigned() {
//        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return sessionRepository.find
//    }

//    public List<SessionWithThesisesDTO> getAllWithThesises() {
//        Long userId = (Long) SecurityContextHolder.getDeferredContext().get().getAuthentication().getPrincipal();
//        List<Session> sessions = sessionRepository.findSessionsWithUserRoles(userId);
//
//        return sessions.stream().map(session -> {
//            SessionWithThesisesDTO dto = new SessionWithThesisesDTO();
//            dto.setId(session.getId());
//            dto.setStartDate(session.getStartDate());
//            dto.setEndDate(session.getEndDate());
//
//
//        })
//    }
}
