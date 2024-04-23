package com.apirest.springsecuritydemo2.infra.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
