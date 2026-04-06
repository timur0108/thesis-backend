package ee.timur.thesis.security;

import ee.timur.thesis.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    public JwtService() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRefreshToken(String name) {
        Date createdAt = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);

        return Jwts.builder()
                .subject(name)
                .claim("type", "refresh")
                .issuedAt(createdAt)
                .expiration(expiryDate)
                .signWith(getKey())
                .compact();
    }

    public String generateAccessToken(User user) {

        String username = user.getEmail();
        return Jwts.builder()
                .claims()
                .subject(username)
                .add("type", "access")
                .add("email", user.getEmail())
                .add("userId", user.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateAccessToken(String token) {
        return (
                !isTokenExpired(token)
                && extractClaim(token, claims -> claims.get("type", String.class))
                .equals("access")
        );
    }

    public boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String userName = extractName(token);
        return (userName.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && extractClaim(token, claims -> claims.get("type", String.class))
                .equals("refresh"));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }
}
