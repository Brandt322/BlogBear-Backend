package com.blogbear.BlogBearBackend.Exception;

import com.blogbear.BlogBearBackend.Utility.DetailErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailErrors> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BlogAppException.class)
    public ResponseEntity<DetailErrors> handleBlogAppException(
            ResourceNotFoundException exception,
            WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<DetailErrors> handleGlobalException(
            ResourceNotFoundException exception,
            WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
