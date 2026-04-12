package ee.timur.thesis.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewThesisDTO {

    private String levelOfStudies;
    private String curriculum;
    private String languageOfThesis;
    private Integer volumeEcts;
    private String titleEstonian;
    private String titleEnglish;
    private Long sessionId;
    private Long studentId;
    private Long reviewerId;
    private Long supervisorId;
    private List<Long> coSupervisorIds;
}
