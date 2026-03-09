package ee.timur.thesis.service;

import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.mapper.ThesisMapper;
import ee.timur.thesis.repository.ThesisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThesisService {

    private final ThesisRepository thesisRepository;
    private final ThesisMapper thesisMapper;

    public List<ThesisDTO> getAllThesises() {
        return thesisRepository.findAll().stream().map(thesisMapper::toDTO).toList();
    }

    public ThesisDTO getThesisById(Long id) {
        return thesisMapper.toDTO(
                thesisRepository.findById(id).orElseThrow()
        );
    }
}
