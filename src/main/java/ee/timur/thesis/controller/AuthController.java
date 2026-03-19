package ee.timur.thesis.controller;

import ee.timur.thesis.dto.AuthResult;
import ee.timur.thesis.dto.LoginRequestDTO;
import ee.timur.thesis.dto.LoginResponseDTO;
import ee.timur.thesis.dto.RefreshTokenResult;
import ee.timur.thesis.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse httpServletResponse) {
        AuthResult jwtDTO = authService.login(loginRequestDTO);
        ResponseCookie refreshTokenCookie = ResponseCookie.from("REFRESH_TOKEN", jwtDTO.refreshToken())
                .httpOnly(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(Duration.ofDays(7))
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        ResponseCookie accessTokenCookie = ResponseCookie.from("ACCESS_TOKEN", jwtDTO.accessToken())
                .httpOnly(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(Duration.ofMinutes(15))
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setEmail(jwtDTO.email());
        loginResponseDTO.setRole(jwtDTO.role());

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(
            @CookieValue("REFRESH_TOKEN") String refreshToken, HttpServletResponse response) {

        RefreshTokenResult jwtDTO = authService.refreshTokens(refreshToken);

        ResponseCookie refreshTokenCookie =
                ResponseCookie.from("REFRESH_TOKEN", jwtDTO.refreshToken())
                        .httpOnly(true)
                        .path("/")
                        .sameSite("Strict")
                        .maxAge(Duration.ofDays(7))
                        .build();

        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        ResponseCookie accessTokenCookie = ResponseCookie.from("ACCESS_TOKEN", jwtDTO.accessToken())
                .httpOnly(true)
                .path("/")
                .sameSite("strict")
                .maxAge(Duration.ofMinutes(15))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());


        return ResponseEntity.ok().build();
    }
}
