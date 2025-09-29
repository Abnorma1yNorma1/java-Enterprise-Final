package by.it_academy.jd2.Mk_jd2_111_25.account_service.controller.utils;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.exceptions.CustomJwtException;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.config.properties.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
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
        String userSecretHex = property.getSecretsMap().get("user-secret");
        byte[] userSecretBytes = HexConverter.hexStringToByteArray(userSecretHex);
        SecretKey secretKey = Keys.hmacShaKeyFor(userSecretBytes);

        return Jwts.builder()
                .subject(userID.toString())
                .issuedAt(Date.from(Instant.now()))
                .issuer(property.getIssuer())
                .expiration(Date.from(Instant.now().plusSeconds(property.getExpiration())))
                .claim("token_type", USER_TOKEN_TYPE)
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public String generateServiceToken(){
        String serviceSecretHex = property.getSecretsMap().get("service-secret");
        byte[] serviceSecretBytes = HexConverter.hexStringToByteArray(serviceSecretHex);
        SecretKey secretKey = Keys.hmacShaKeyFor(serviceSecretBytes);
        return Jwts.builder()
                .issuedAt(Date.from(Instant.now()))
                .issuer(property.getIssuer())
                .expiration(Date.from(Instant.now().plusSeconds(property.getExpiration())))
                .claim("token_type", SERVICE_TOKEN_TYPE)
                .signWith(secretKey, Jwts.SIG.HS512)
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
        String secretHex = property.getSecretsMap().get(secretKeyName);
        if (secretHex == null || secretHex.isEmpty()) {
            throw new IllegalArgumentException("JWT secret key for '" + secretKeyName + "' is not configured.");
        }
        byte[] secretBytes = HexConverter.hexStringToByteArray(secretHex);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretBytes);
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();
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
        String secretHex = property.getSecretsMap().get(secretKeyName);
        byte[] secretBytes = HexConverter.hexStringToByteArray(secretHex);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretBytes);
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();
            return expectedTokenType.equals(claims.get("token_type"));
        } catch (JwtException  ex) {
            //logger.error("JWT validation failure: - {}", ex.getMessage());
        }
        return false;
    }

    public static class HexConverter {
        public static byte[] hexStringToByteArray(String s) {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            return data;
        }
    }
}
