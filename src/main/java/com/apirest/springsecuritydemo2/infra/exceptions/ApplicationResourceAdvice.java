package com.apirest.springsecuritydemo2.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Essa anotação permite definir métodos que manipulam exceções específicas ou exceções gerais.
public class ApplicationResourceAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ApiError handleBusinessException(BusinessException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleUnauthorizedException(UnauthorizedException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleRuntimeException(RuntimeException exception) {
        return new ApiError(exception.getMessage());
    }

}
