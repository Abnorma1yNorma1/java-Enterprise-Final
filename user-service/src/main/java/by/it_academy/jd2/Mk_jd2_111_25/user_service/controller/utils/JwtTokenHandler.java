package by.it_academy.jd2.Mk_jd2_111_25.user_service.controller.utils;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.config.properties.JwtProperty;
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
    private static final String USER_TOKEN_TYPE = "user";
    private static final String SERVICE_TOKEN_TYPE = "service";

    public String generateUserToken(UUID userID){
        return Jwts.builder()
                .setSubject(userID.toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setIssuer(property.getIssuer())
                .setExpiration(Date.from(Instant.now().plusSeconds(property.getExpiration())))
                .claim("token_type", USER_TOKEN_TYPE)
                .signWith(SignatureAlgorithm.ES256, property.getSecretsMap().get("user-secret"))
                .compact();
    }

    public String generateServiceToken(){
        return Jwts.builder()
                .setIssuedAt(Date.from(Instant.now()))
                .setIssuer(property.getIssuer())
                .setExpiration(Date.from(Instant.now().plusSeconds(property.getExpiration())))
                .claim("token_type", SERVICE_TOKEN_TYPE)
                .signWith(SignatureAlgorithm.ES256, property.getSecretsMap().get("service-secret"))
                .compact();
    }

    public UUID getUserId(String token){
        Claims claims = getClaims(token, "user-secret", USER_TOKEN_TYPE);
        return (claims != null) ? UUID.fromString(claims.getSubject()) : null;
    }

    public Claims getServiceTokenClaims(String token) {
        return getClaims(token, "service-secret", SERVICE_TOKEN_TYPE);
    }

    public Claims getClaims(String token, String secretKeyName, String expectedTokenType) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(property.getSecretsMap().get(secretKeyName))
                    .parseClaimsJws(token)
                    .getBody();
            if (!expectedTokenType.equals(claims.get("token_type"))) {
                throw new JwtException("Incorrect token type");
            }
            return claims;
        } catch (JwtException ex) {
            throw new CustomJwtException("Token validation failed", ex);
//            logger.error("Invalid token claims extraction attempt (%s): %s\n", secretKeyName, ex.getMessage());
        }
    }

    public boolean validateUserToken(String token) {
        return validate(token, "user-secret", USER_TOKEN_TYPE);
    }

    public boolean validateServiceToken(String token) {
        return validate(token, "service-secret", SERVICE_TOKEN_TYPE);
    }

    private boolean validate(String token, String secretKeyName, String expectedTokenType) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(property.getSecretsMap().get(secretKeyName))
                    .parseClaimsJws(token)
                    .getBody();
            return expectedTokenType.equals(claims.get("token_type"));
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
