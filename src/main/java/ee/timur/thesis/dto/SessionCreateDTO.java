package ee.timur.thesis.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionCreateDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> committeeMemberIds;
    private Long headOfCommitteeId;
}
