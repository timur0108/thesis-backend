package ee.timur.thesis.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ThesisDTO {

    private Long id;
    private String studentName;
    private String levelOfStudies;
    private String languageOfThesis;
    private Integer volumeEcts;
    private String titleEstonian;
    private String titleEnglish;
    private String supervisorName;
}
