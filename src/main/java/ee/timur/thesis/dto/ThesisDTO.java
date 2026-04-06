package ee.timur.thesis.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ThesisDTO {

    private Long id;
    private String studentName;
    private String levelOfStudies;
    private String languageOfThesis;
    private Integer volumeEcts;
    private String titleEstonian;
    private String titleEnglish;
    private String finalGradeLetter;
    private Integer finalGradeNumber;
    private List<String> roles;
    private Long sessionId;
    private LocalDate sessionStartDate;
    private LocalDate sessionEndDate;
}
