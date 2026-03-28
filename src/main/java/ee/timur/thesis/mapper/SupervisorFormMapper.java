package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.SupervisorFormDTO;
import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.model.SupervisorForm;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupervisorFormMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "thesis", source = "thesis")
    @Mapping(target = "user", source = "user")
    SupervisorForm toEntityFromThesisCreateDTO(ThesisCreateDTO thesisCreateDTO, Thesis thesis, User user);

    SupervisorFormDTO toDTO(SupervisorForm supervisorForm);
}
