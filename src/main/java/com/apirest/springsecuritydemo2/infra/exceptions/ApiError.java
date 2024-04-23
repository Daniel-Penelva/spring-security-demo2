package com.apirest.springsecuritydemo2.infra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
}

/*Essa classe vai ser responsável por fazer a formatação do erro e devolver para o cliente.*/