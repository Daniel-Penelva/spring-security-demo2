package com.apirest.springsecuritydemo2.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apirest.springsecuritydemo2.dtos.AuthDto;
import com.apirest.springsecuritydemo2.models.Usuario;
import com.apirest.springsecuritydemo2.repositories.UsuarioRepository;
import com.apirest.springsecuritydemo2.service.AutenticacaoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AutenticacaoServiceImp implements AutenticacaoService {

    private UsuarioRepository usuarioRepository;

    /* O método loadUserByUsername(String username) é responsável por carregar o objeto UserDetails para um determinado nome de usuário de um
     * repositório de usuários, como um banco de dados, diretório LDAP ou estrutura de dados em memória. O método recebe um parâmetro String
     * representando o nome de usuário e retorna um objeto UserDetails representando o usuário com esse nome de usuário. */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }


    /*Este método obterToken é responsável por obetr um token JWT (JSON Web Token) para um objeto Usuario*/
    @Override
    public String obterToken(AuthDto authDto) {
        Usuario usuario = usuarioRepository.findByLogin(authDto.login());
        return gerarTokenJwt(usuario);
    }


    /*Este método gerarTokenJwt é responsável por gerar um token JWT (JSON Web Token) para um objeto Usuario*/
    public String gerarTokenJwt(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");                // Cria uma instância do Algorithm usando o algoritmo HMAC256 e uma chave secreta ("my-secret"). 

            return JWT.create()                                                         // Cria um novo objeto JWT usando o método JWT.create().
                    .withIssuer("spring-security-demo2")                         // Define o emissor do token, no caso, especifica que o token é emitido pelo meu projeto "spring-security-demo2".
                    .withSubject(usuario.getLogin())                                    // Define o assunto do token como o login do objeto Usuario.
                    .withExpiresAt(geraDataExpiracao())                                 // Define a data de expiração do token como o valor retornado pelo método geraDataExpiracao().
                    .sign(algorithm);                                                   // Assina o token usando o objeto algorithm criado anteriormente.

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao tentar gerar token!" + exception.getMessage());
        }
    }

    /* O método validaTokeJwt verifica a validade do token JWT com base no algoritmo e emissor especificados. Se o token for válido, ele retorna 
     * o assunto do token. Se o token for inválido, ele retorna uma string vazia. */
    public String validaTokeJwt(String token){

        try {
            
            Algorithm algorithm = Algorithm.HMAC256("my-secret");        // Cria uma instância do Algorithm usando o algoritmo HMAC256 e uma chave secreta ("my-secret"). 
            return JWT.require(algorithm)                                       // Inicia a verificação do token com o algoritmo especificado.
                    .withIssuer("spring-security-demo2")                 // Define o emissor do token, no caso, especifica que o token é emitido pelo meu projeto "spring-security-demo2".
                    .build()                                                    // Constrói o objeto de verificação do token com as configurações especificadas.
                    .verify(token)                                              // Verifica a validade do token com base nas configurações especificadas.
                    .getSubject();                                              // Retorna o assunto do token se ele for válido. 

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    // Este método gera a data de expiração do token
    private Instant geraDataExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

}
