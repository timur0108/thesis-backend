package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewerGradeMapper {

    @Mapping(target = "thesis", source = "thesis")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    ReviewerGrade toEntity(ReviewerGradeDTO dto, Thesis thesis, User user);

    @Mapping(target = "thesisId", source = "thesis.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "secondName", source = "user.secondName")
    ReviewerGradeDTO toDTO(ReviewerGrade reviewerGrade);
}
