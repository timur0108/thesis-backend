package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.model.Thesis;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ThesisMapper {

    ThesisDTO toDTO(Thesis thesis);
}
