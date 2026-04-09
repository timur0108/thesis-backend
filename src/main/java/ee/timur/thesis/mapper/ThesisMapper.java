package ee.timur.thesis.mapper;

import ee.timur.thesis.dto.ThesisCreateDTO;
import ee.timur.thesis.dto.ThesisDTO;
import ee.timur.thesis.model.Thesis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThesisMapper {

    @Mapping(target = "finalGradeLetter", source = "finalGrade.letterGrade")
    @Mapping(target = "finalGradeNumber", source = "finalGrade.totalScore")
    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "sessionStartDate", source = "session.startDate")
    @Mapping(target = "sessionEndDate", source = "session.endDate")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "studentSecondName", source = "student.secondName")
    @Mapping(target = "studentEmail", source = "student.email")
    @Mapping(target = "studentNumber", source = "student.studentNumber")
    ThesisDTO toDTO(Thesis thesis);

    @Mapping(target = "id", ignore = true)
    Thesis toEntity(ThesisCreateDTO dto);
}
