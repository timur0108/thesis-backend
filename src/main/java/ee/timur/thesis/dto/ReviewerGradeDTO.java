package ee.timur.thesis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReviewerGradeDTO {

    private Long thesisId;
    private BigDecimal contentScore;
    private String contentReasoning;
    private BigDecimal complexityScore;
    private String complexityReasoning;
    private BigDecimal appearanceScore;
    private String appearanceReasoning;
    private String evaluationSummary;
    private String questions;
    private String name;
    private String secondName;
}
