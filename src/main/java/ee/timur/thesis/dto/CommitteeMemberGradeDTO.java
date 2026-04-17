package ee.timur.thesis.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommitteeMemberGradeDTO {

    private Long id;
    private Long thesisId;
    private BigDecimal contentScore;
    private BigDecimal complexityScore;
    private BigDecimal appearanceScore;
    private BigDecimal presentationScore;
    private String name;
    private String secondName;
    private Boolean visibleToOthers;
}
