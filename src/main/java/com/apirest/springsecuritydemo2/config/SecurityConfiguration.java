package com.apirest.springsecuritydemo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration        // Indica que a classe é uma classe de configuração Spring, permitindo a definição de beans e configurações para a aplicação.
@EnableWebSecurity    // Habilita a segurança web no aplicativo Spring, permitindo a configuração de políticas de segurança para a aplicação.
public class SecurityConfiguration {

    @Bean  
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity                                                                                      // Representa a configuração de segurança para as requisições HTTP.
                .csrf(csrf -> csrf.disable())                                                                    // Desabilita a proteção CSRF (Cross-Site Request Forgery) para evitar ataques CSRF, o que pode ser apropriado em certos cenários, como APIs REST stateless.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))    // Define a política de gerenciamento de sessão como STATELESS, indicando que a aplicação não deve criar ou usar sessões HTTP, tornando-a stateless.
                .build();                                                                                        // Finaliza a configuração do objeto httpSecurity e retorna a instância de SecurityFilterChain configurada de acordo com as políticas definidas
    }

}

/* ANOTAÇÃO:
Só essa configuração já desabilita todas as seguranças q o Spring Security habilitou tanto na autenticação (verificar a identidade de um usuário)
quanto na autorização (determinar as ações que um usuário, dispositivo ou sistema está autorizado a realizar). Logo já é possível acessar a 
requisição "http://localhost:8080/usuarios" sem ser bloqueado pela página de login do Spring Security.
*/