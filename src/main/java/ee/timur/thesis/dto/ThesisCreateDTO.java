package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class ThesisCreateDTO {

    private String studentName;
    private String levelOfStudies;
    private String curriculum;
    private String languageOfThesis;
    private Integer volumeEcts;
    private String titleEstonian;
    private String titleEnglish;
    private String contextOfResearch;
    private String strengthOfThesis;
    private String studentContribution;
    private String limitationOfThesis;
    private String cooperation;
    private String additionalComments;
}
