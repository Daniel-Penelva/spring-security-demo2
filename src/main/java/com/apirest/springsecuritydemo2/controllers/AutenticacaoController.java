package com.apirest.springsecuritydemo2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.springsecuritydemo2.dtos.AuthDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AutenticacaoController {

    /*A interface AuthenticationManager é responsável por autenticar um usuário validando suas credenciais, como nome de usuário (login) e senha. O 
     * AuthenticationManager é chamado para realizar a autenticação. Ele recebe um objeto Authentication, que contém as credenciais do usuário, 
     * e retorna um objeto Authentication que representa o usuário autenticado. Se a autenticação for bem-sucedida, o objeto Authentication 
     * conterá os detalhes do usuário, como seu nome de usuário, autorizações e outras informações. Se a autenticação falhar, o objeto 
     * Authentication será marcado como "autenticado=false". A interface AuthenticationManager define um único método, authenticate(Authentication authentication), 
     * que realiza a autenticação. Este método recebe um objeto Authentication como parâmetro e retorna um objeto Authentication. O objeto 
     * Authentication passado como parâmetro contém as credenciais do usuário, como seu nome de usuário e senha. O objeto Authentication 
     * retornado pelo método authenticate contém os detalhes do usuário, como seu nome de usuário, autorizações e outras informações.
    */
    private AuthenticationManager authenticationManager;

    // http://localhost:8080/auth 
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDto authDto){
        var usuarioAuthenticationToken = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());  // Representar a autenticação de um usuário baseado em nome de usuário e senha. Vai ser buscado no UsuarioRepository, onde valida a senha e devolve o token.
        authenticationManager.authenticate(usuarioAuthenticationToken);

        return "Autenticado token com sucesso!";
    }
    
}
