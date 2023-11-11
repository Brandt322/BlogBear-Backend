package com.blogbear.BlogBearBackend.Exception;

import com.blogbear.BlogBearBackend.Utility.DetailErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailErrors> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BlogAppException.class)
    public ResponseEntity<DetailErrors> handleBlogAppException(ResourceNotFoundException exception, WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<DetailErrors> handleGlobalException(ResourceNotFoundException exception, WebRequest request) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
