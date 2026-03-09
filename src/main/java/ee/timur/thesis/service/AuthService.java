package ee.timur.thesis.service;

import ee.timur.thesis.dto.AuthResult;
import ee.timur.thesis.dto.LoginRequestDTO;
import ee.timur.thesis.dto.RefreshTokenResult;
import ee.timur.thesis.model.User;
import ee.timur.thesis.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Transactional
    public AuthResult login(LoginRequestDTO loginRequestDTO) {
        // exception handling
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) userDetailsService.loadUserByUsername(email);

        String role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().getFirst();
        String accessToken = jwtService.generateAccessToken(user.getUsername(), role);
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return new AuthResult(accessToken, refreshToken, user.getEmail(), user.getRole().getRoleName());
    }

    // exception handling
    public RefreshTokenResult refreshTokens(String refreshToken) {
        String username = jwtService.extractName(refreshToken);

        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (!jwtService.validateRefreshToken(refreshToken, user)) {
            throw new RuntimeException();
        }

        String role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().getFirst();
        String newAccessToken = jwtService.generateAccessToken(user.getUsername(), role);
        String newRefreshToken = jwtService.generateRefreshToken(user.getUsername());

        return new RefreshTokenResult(newAccessToken, newRefreshToken);
    }

}
