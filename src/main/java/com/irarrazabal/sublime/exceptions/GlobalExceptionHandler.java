package com.irarrazabal.sublime.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockExceptions.class)
    public ResponseEntity<ApiError> handlerStockException(StockExceptions ex){

       ApiError error = new ApiError(ex.getMessage(),400);
       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SaleWithoutQuantity.class)
    public ResponseEntity<ApiError> handlerStockException(SaleWithoutQuantity ex){

       ApiError error = new ApiError(ex.getMessage(),400);
       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handlerProductNotFoundException(ProductNotFoundException ex){
        ApiError error = new ApiError(ex.getMessage(),400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}
