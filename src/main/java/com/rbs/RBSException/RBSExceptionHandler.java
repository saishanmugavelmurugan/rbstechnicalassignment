package com.rbs.RBSException;

import com.rbs.pojo.RestApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RBSExceptionHandler {
    @ExceptionHandler(value = { AccountNumberNotFoundException.class, InsufficientFundException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new RestApiResponse(HttpStatus.FORBIDDEN, "300", "Failed",ex.getMessage()),HttpStatus.FORBIDDEN);
    }
}
