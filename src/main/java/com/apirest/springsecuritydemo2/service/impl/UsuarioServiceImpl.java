package com.apirest.springsecuritydemo2.service.impl;

import org.springframework.stereotype.Service;

import com.apirest.springsecuritydemo2.dtos.UsuarioDto;
import com.apirest.springsecuritydemo2.models.Usuario;
import com.apirest.springsecuritydemo2.repositories.UsuarioRepository;
import com.apirest.springsecuritydemo2.service.UsuarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        
        Usuario usuarioExiste = usuarioRepository.findByLogin(usuarioDto.login());
        if (usuarioExiste != null) { // verifica para não deixar cadastrar o mesmo usuário com o mesmo login.
            throw new RuntimeException("Usuário já existe!");
        }
        
        Usuario usuario = new Usuario(usuarioDto.nome(), usuarioDto.login(), usuarioDto.senha());
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha());
    }

}
