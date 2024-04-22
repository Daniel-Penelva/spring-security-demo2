package com.apirest.springsecuritydemo2.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.springsecuritydemo2.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AutenticacaoServiceImp implements UserDetailsService{

    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }
    
}

/* 
A interface UserDetailsService carrega um objeto UserDetails para um determinado nome de usuário. O objeto UserDetails contém informações 
sobre um usuário, como seu nome de usuário, senha e autorizações, que podem ser usadas para fins de autenticação e autorização.

O método loadUserByUsername(String username) é responsável por carregar o objeto UserDetails para um determinado nome de usuário de um 
repositório de usuários, como um banco de dados, diretório LDAP ou estrutura de dados em memória. O método recebe um parâmetro String 
representando o nome de usuário e retorna um objeto UserDetails representando o usuário com esse nome de usuário.
*/
