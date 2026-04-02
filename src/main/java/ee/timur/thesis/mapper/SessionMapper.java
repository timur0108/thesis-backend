package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.SessionCreateDTO;
import ee.timur.thesis.dto.SessionDTO;
import ee.timur.thesis.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionDTO toDTO(Session entity);

    @Mapping(target = "id", ignore = true)
    Session toEntity(SessionCreateDTO dto);
}
