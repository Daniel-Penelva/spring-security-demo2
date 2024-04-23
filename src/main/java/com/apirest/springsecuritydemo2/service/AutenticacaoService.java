package com.apirest.springsecuritydemo2.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.apirest.springsecuritydemo2.dtos.AuthDto;
import com.apirest.springsecuritydemo2.dtos.TokenResponseDto;

public interface AutenticacaoService extends UserDetailsService{

    public TokenResponseDto obterToken(AuthDto authDto);

    public String validaTokeJwt(String token);
    
    public TokenResponseDto obterRefreshToken(String refreshToken);

}

/* 
A interface UserDetailsService carrega um objeto UserDetails para um determinado nome de usuário. O objeto UserDetails contém informações 
sobre um usuário, como seu nome de usuário, senha e autorizações, que podem ser usadas para fins de autenticação e autorização.
*/