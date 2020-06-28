package com.example.springMicroService.controller;

import com.example.springMicroService.customException.ExceptionResponse;
import com.example.springMicroService.customException.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//to allow all controller used that by adding @ControllerAdvice
@ControllerAdvice
@RestController
public class  CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //Handle All Exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
      ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
     return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//User Not Found Exception
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleAllException(UserNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
///Handle when user enter Invlaid Data
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),
              ex.getBindingResult().toString());
        //getBindingResult used to valid data
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
