package com.makar.blablacar.handler;

import com.makar.blablacar.exception.EntityNotFoundException;
import com.makar.blablacar.exception.NonUniqueFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Entity not found");
        body.put("id", ex.getCauseId());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonUniqueFieldException.class)
    public ResponseEntity<Object> handleNonUniqueField(NonUniqueFieldException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Cannot duplicate filed value");
        body.put("field", ex.getFieldName());
        body.put("value", ex.getFieldValue());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
