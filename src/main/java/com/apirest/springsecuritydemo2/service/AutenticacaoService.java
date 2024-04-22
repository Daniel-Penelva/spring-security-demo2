package com.apirest.springsecuritydemo2.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.apirest.springsecuritydemo2.dtos.AuthDto;

public interface AutenticacaoService extends UserDetailsService{

    public String obterToken(AuthDto authDto);

}

/* 
A interface UserDetailsService carrega um objeto UserDetails para um determinado nome de usuário. O objeto UserDetails contém informações 
sobre um usuário, como seu nome de usuário, senha e autorizações, que podem ser usadas para fins de autenticação e autorização.
*/