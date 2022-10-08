package dev.lam.gira.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    private final String jwtKey = "fosaodfdofdijfddsdsdsdsdsdsdsdsdss";

    public String generateJwt(String username) {
        Date currDate = new Date();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currDate)
                .setExpiration(new Date(currDate.getTime() + 86400000))
                .signWith(
                        Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256) // Key Cloak, Vault
                .compact();
    }
}
