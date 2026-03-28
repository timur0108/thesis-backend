package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class FinalGradeDTO {

    private Integer contentScore;
    private Integer complexityScore;
    private Integer appearanceScore;
    private Integer presentationScore;
    private Integer totalScore;
    private String letterGrade;
    private Long thesisId;
}
