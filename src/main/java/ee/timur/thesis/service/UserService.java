package ee.timur.thesis.service;

import ee.timur.thesis.dto.UserDTO;
import ee.timur.thesis.mapper.UserMapper;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.CommitteeMemberGradeRepository;
import ee.timur.thesis.repository.ThesisRepository;
import ee.timur.thesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CommitteeMemberGradeRepository committeeMemberGradeRepository;
    private final ThesisRepository thesisRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .filter(user -> !user.getIsAdmin())
                .map(userMapper::toDTO)
                .toList();
    }

    public List<UserDTO> getUnsubmittedCommitteeMembers(Long thesisId) {
        return userRepository.findCommitteeMembersByThesis(thesisId).stream()
                .filter(user -> committeeMemberGradeRepository.findGradeByThesisAndUser(thesisId, user.getId()).isEmpty())
                .filter(user -> {
                    List<String> roles = thesisRepository.getRolesFromThesis(thesisId, user.getId());
                    return !roles.contains("SUPERVISOR") && !roles.contains("CO-SUPERVISOR");
                })
                .map(userMapper::toDTO)
                .toList();

    }

    public List<UserDTO> getCommitteeMembersBySession(Long sessionId) {
        return userRepository.findCommitteeMembersBySessionId(sessionId).stream().map(userMapper::toDTO).toList();
    }

    public UserDTO getHeadOfCommitteeBySession(Long sessionId) {
        return userMapper.toDTO(userRepository.findHeadOfCommitteeBySessionId(sessionId).orElseThrow());
    }
}
