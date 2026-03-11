package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.CommitteeMemberGradeDTO;
import ee.timur.thesis.model.CommitteeMemberGrade;
import ee.timur.thesis.model.Thesis;
import ee.timur.thesis.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommitteeMemberGradeMapper {

    @Mapping(target = "thesis", source = "thesis")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visibleToOthers", constant = "false")
    CommitteeMemberGrade toEntity(CommitteeMemberGradeDTO dto, Thesis thesis, User user);

    @Mapping(target = "thesisId", source = "thesis.id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "secondName", source = "user.secondName")
    CommitteeMemberGradeDTO toDTO(CommitteeMemberGrade committeeMemberGrade);
}
