package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class ReviewerGradeDTO {

    private Long thesisId;
    private Integer contentScore;
    private String contentReasoning;
    private Integer complexityScore;
    private String complexityReasoning;
    private Integer appearanceScore;
    private String appearanceReasoning;
    private String evaluationSummary;
    private String questions;
    private String name;
    private String secondName;
}
