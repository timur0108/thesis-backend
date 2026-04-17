package ee.timur.thesis.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ThesisDTO {

    private Long id;
    private String studentName;
    private String studentSecondName;
    private String studentEmail;
    private String studentNumber;
    private String levelOfStudies;
    private String languageOfThesis;
    private Integer volumeEcts;
    private String titleEstonian;
    private String titleEnglish;
    private String finalGradeLetter;
    private BigDecimal finalGradeNumber;
    private List<String> roles;
    private List<UserDTO> committeeMembers;
    private UserDTO headOfCommittee;
    private UserDTO reviewer;
    private UserDTO supervisor;
    private List<UserDTO> coSupervisors;
    private Long sessionId;
    private LocalDate sessionStartDate;
    private LocalDate sessionEndDate;
}
