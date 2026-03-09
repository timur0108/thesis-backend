package ee.timur.thesis.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;
    private String password;
}
