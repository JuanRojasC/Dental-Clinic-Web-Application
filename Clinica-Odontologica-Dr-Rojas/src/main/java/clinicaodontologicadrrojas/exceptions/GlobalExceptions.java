package clinicaodontologicadrrojas.exceptions;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.HashMap;

@ControllerAdvice
@Log4j
public class GlobalExceptions implements Serializable{

    @ExceptionHandler
    public ResponseEntity<HashMap> notFoundException(ResourceNotFoundException e){
        log.error(e.getMessage());
        HashMap<String, Object> response = new HashMap<>();
        response.put("Status", 404);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<HashMap> userAlreadyExists(UserAlreadyExistsException e){
        log.error(e.getMessage());
        HashMap<String, Object> response = new HashMap<>();
        response.put("Status", 409);
        response.put("Message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
