package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.CommitteeMemberGradeDTO;
import ee.timur.thesis.dto.FinalGradeDTO;
import ee.timur.thesis.model.CommitteeMemberGrade;
import ee.timur.thesis.model.FinalGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinalGradeMapper {

    @Mapping(target = "thesisId", source = "thesis.id")
    FinalGradeDTO toDTO(FinalGrade finalGrade);

    @Mapping(target = "thesis", source = "thesis")
    @Mapping(target = "id", ignore = true)
    FinalGrade toEntity(FinalGradeDTO dto, Thesis thesis);
}
