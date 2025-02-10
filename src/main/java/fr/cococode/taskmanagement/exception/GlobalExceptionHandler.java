package fr.cococode.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Le stéréotype @ExceptionHandler indique une méthode de gestion d'exception.
// L’annotation permet de préciser la classe de l’exception que la méthode peut gérer

public class GlobalExceptionHandler {
	
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFoundException(TaskNotFoundException ex) {
    	
        Map<String, String> errors = new HashMap<>();
        
        errors.put("error", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
    	
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors()
        	.forEach(error -> {
        		String fieldName = ((FieldError) error).getField();
            	String errorMessage = error.getDefaultMessage();
            	errors.put(fieldName, errorMessage);
        	});
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
