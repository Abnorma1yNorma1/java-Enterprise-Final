package by.it_academy.jd2.Mk_jd2_111_25.mail_service.common.Exceptions;

import io.jsonwebtoken.JwtException;

public class CustomJwtException extends JwtException {
    public CustomJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
