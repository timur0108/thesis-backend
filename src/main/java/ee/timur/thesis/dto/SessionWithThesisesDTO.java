package ee.timur.thesis.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionWithThesisesDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ThesisDTO> thesises;
    private List<String> roles;
}
