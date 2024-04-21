package com.apirest.springsecuritydemo2.controllers;

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

    // http://localhost:8080/usuarios
    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {

        return usuarioService.salvar(usuarioDto);
    }
}
