package com.apirest.springsecuritydemo2.dtos;

import com.apirest.springsecuritydemo2.enums.Role;

public record UsuarioDto(String nome, String login, String senha, Role role) {
}
