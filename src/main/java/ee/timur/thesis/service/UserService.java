package ee.timur.thesis.service;

import ee.timur.thesis.dto.UserDTO;
import ee.timur.thesis.mapper.UserMapper;
import ee.timur.thesis.model.User;
import ee.timur.thesis.repository.CommitteeMemberGradeRepository;
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

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public List<UserDTO> getUnsubmittedCommitteeMembers(Long thesisId) {
        return userRepository.findCommitteeMembersByThesis(thesisId).stream()
                .filter(user -> committeeMemberGradeRepository.findGradeByThesisAndUser(thesisId, user.getId()).isEmpty())
                .map(userMapper::toDTO)
                .toList();

    }
}
