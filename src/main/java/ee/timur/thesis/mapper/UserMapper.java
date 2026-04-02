package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.UserDTO;
import ee.timur.thesis.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User entity);
}
