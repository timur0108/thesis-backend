package ee.timur.thesis.service;

import ee.timur.thesis.dto.UserDTO;
import ee.timur.thesis.mapper.UserMapper;
import ee.timur.thesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
