package ee.timur.thesis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinalGradeDTO {

    private Integer contentScore;
    private Integer complexityScore;
    private Integer appearanceScore;
    private Integer presentationScore;
    private BigDecimal totalScore;
    private String letterGrade;
    private Long thesisId;
}
