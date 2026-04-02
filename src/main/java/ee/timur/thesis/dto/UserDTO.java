package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String secondName;
    private String email;
}
