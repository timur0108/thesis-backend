package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.ReviewerGradeDTO;
import ee.timur.thesis.model.ReviewerGrade;
import ee.timur.thesis.model.Thesis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewerGradeMapper {

    @Mapping(target = "thesis", source = "thesis")
    @Mapping(target = "id", ignore = true)
    ReviewerGrade toEntity(ReviewerGradeDTO dto, Thesis thesis);
}
