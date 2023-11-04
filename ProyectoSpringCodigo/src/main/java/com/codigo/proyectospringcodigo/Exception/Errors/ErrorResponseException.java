package com.codigo.proyectospringcodigo.Exception.Errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseException extends Exception{
    HttpStatus httpStatus;
    int status;
    public ErrorResponseException(String message, int status, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.status = status;
    }
}
