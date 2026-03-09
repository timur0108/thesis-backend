package ee.timur.thesis.dto;

public record AuthResult(String accessToken, String refreshToken, String email, String role) {
}