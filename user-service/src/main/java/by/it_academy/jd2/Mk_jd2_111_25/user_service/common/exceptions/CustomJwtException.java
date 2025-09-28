package by.it_academy.jd2.Mk_jd2_111_25.user_service.common.exceptions;

import io.jsonwebtoken.JwtException;

public class CustomJwtException extends JwtException {
    public CustomJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
