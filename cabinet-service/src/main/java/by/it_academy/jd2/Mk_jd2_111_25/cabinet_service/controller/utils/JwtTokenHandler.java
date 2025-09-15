package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.config.properties.JwtProperty;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenHandler {

    private final JwtProperty property;

    public String generate(UUID userID){
        return Jwts.builder()
                .setSubject(userID.toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setIssuer(property.getIssuer())
                .setExpiration(Date.from(Instant.now().plusSeconds(property.getExpiration())))
                .signWith(SignatureAlgorithm.ES256, property.getSecret())
                .compact();
    }

    public UUID getId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return UUID.fromString(claims.getSubject());
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public String getIssuer(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getIssuer();
    }



    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
