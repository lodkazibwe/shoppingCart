package com.kazam.shopingcart.exceptions;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CartExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),
                "Method Argument Not Valid",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,  status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),"Type Mismatch",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),
                "Missing Path Variable",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),
                "No Handler Found",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),
                "Request Method  Not Supported",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CartErrorDetails cartErrorDetails=new CartErrorDetails(new Date(),
                "Http Message Not Readable",ex.getMessage());
        return new ResponseEntity<>(cartErrorDetails,status);
    }

}
