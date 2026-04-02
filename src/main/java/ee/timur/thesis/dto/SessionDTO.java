package ee.timur.thesis.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
}
