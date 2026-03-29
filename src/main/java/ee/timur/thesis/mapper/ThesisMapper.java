package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.model.Thesis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ThesisMapper {

    @Mapping(target = "finalGrade", source = "finalGrade.letterGrade")
    ThesisDTO toDTO(Thesis thesis);

    @Mapping(target = "id", ignore = true)
    Thesis toEntity(ThesisCreateDTO dto);
}
