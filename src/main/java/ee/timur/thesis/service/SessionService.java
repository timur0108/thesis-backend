package ee.timur.thesis.service;

import ee.timur.thesis.dto.SessionCreateDTO;
import ee.timur.thesis.dto.SessionDTO;
import ee.timur.thesis.dto.SessionWithThesisesDTO;
import ee.timur.thesis.mapper.SessionMapper;
import ee.timur.thesis.model.Session;
import ee.timur.thesis.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    public List<SessionDTO> getAll() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDTO)
                .toList();
    }

    public void createSession(SessionCreateDTO dto) {
        Session session = sessionMapper.toEntity(dto);

    }

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
