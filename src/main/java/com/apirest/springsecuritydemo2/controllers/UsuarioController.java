package com.apirest.springsecuritydemo2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.springsecuritydemo2.dtos.UsuarioDto;
import com.apirest.springsecuritydemo2.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    // http://localhost:8080/login  (login: user)
    // http://localhost:8080/usuarios
    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {

        return usuarioService.salvar(usuarioDto);
    }

    
    @GetMapping
    private String getOk(){
        return "Conta acessada!";
    }

    // http://localhost:8080/usuarios/admin
    @GetMapping("/admin")
    private String getAdmin(){
        return "Conta acessada - Permissão de Administrador!";
    }


    // http://localhost:8080/usuarios/user
    @GetMapping("/user")
    private String getUser(){
        return "Conta acessada - Permissão de Usuário!";
    }
}
