package com.tool.store.controller;

import com.tool.store.exception.InvalidToolCodeException;
import com.tool.store.exception.InvalidToolTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        if (ex.hasErrors()) {
            return ex.getAllErrors().get(0).getDefaultMessage();
        }
        return "Bad Request.";
    }

    @ExceptionHandler(value = {InvalidToolCodeException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    protected String handleInvalidToolException(InvalidToolCodeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = {InvalidToolTypeException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    protected String handleInvalidToolTypeException(InvalidToolTypeException ex) {
        return ex.getMessage();
    }
}
