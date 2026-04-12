package ee.timur.thesis.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionFullDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ThesisDTO> theses;
    private String role;
    private List<UserDTO> committeeMembers;
    private UserDTO headOfCommittee;
}
