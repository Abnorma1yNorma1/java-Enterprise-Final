package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex){
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> Map.of("field", err.getField(),
                        "message", err.getDefaultMessage()
                ))
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "logref", "structured_error",
                        "errors", errors
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(List.of(Map.of(
                        "logref", "error",
                        "message", ex.getMessage()
                )));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Authorisation required."));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Provided credentials do not have required permissions."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOther(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(Map.of(
                        "logref", "error",
                        "message", "Server error. Contact the administrator."
                )));
    }
}
