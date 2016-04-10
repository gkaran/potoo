package gk.potoo.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PotooExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity handleGenericException(AccountExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({ MalformedJwtException.class, BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity handleAuthorizationExceptions(Exception exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There has been an error. Please try again later");
    }

}
