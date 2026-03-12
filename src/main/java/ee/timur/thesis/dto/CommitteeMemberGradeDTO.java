package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class CommitteeMemberGradeDTO {

    private Long thesisId;
    private Integer contentScore;
    private Integer complexityScore;
    private Integer appearanceScore;
    private Integer presentationScore;
    private String name;
    private String secondName;
    private Boolean visibleToOthers;
}
