package com.apirest.springsecuritydemo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration        // Indica que a classe é uma classe de configuração Spring, permitindo a definição de beans e configurações para a aplicação.
@EnableWebSecurity    // Habilita a segurança web no aplicativo Spring, permitindo a configuração de políticas de segurança para a aplicação.
public class SecurityConfiguration {

    /*Configura a segurança web no aplicativo Spring*/
    @Bean  
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity                                                                                      // Representa a configuração de segurança para as requisições HTTP.
                .csrf(AbstractHttpConfigurer::disable)                                                           // Desabilita a proteção CSRF (Cross-Site Request Forgery) para evitar ataques CSRF. A classe AbstractHttpConfigurer fornece métodos úteis para configurar as políticas de segurança, como disable() para desabilitar a proteção CSRF.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))    // Define a política de gerenciamento de sessão como STATELESS, indicando que a aplicação não deve criar ou usar sessões HTTP, tornando-a stateless.
                .authorizeHttpRequests(authorize ->                                                              // Inicia a configuração de autorização para as solicitações HTTP, passando um lambda para o método authorizeHttpRequests.
                        authorize                                                                                // Representa a instância de HttpSecurity que será configurada para autorização.
                            .requestMatchers(HttpMethod.GET, "/usuarios")                                        // Define que as políticas de autorização serão aplicadas a solicitações GET na rota "/usuarios".
                                .hasRole("ADMIN")                                                                // Define que apenas usuários com a função "ADMIN" serão autorizados a acessar a rota "/usuarios" com método GET.
                            .requestMatchers(HttpMethod.POST, "/usuarios")                                       // Define que as políticas de autorização serão aplicadas a solicitações HTTP POST para a rota "/usuários".
                                .permitAll()                                                                     // Define que qualquer usuário, autenticado ou não, será autorizado a acessar a rota "/usuários" com método POST.
                            .anyRequest()                                                                        // Define que as políticas de autorização serão aplicadas a qualquer outra solicitação HTTP.
                            .authenticated())                                                                    // Define que apenas usuários autenticados serão autorizados a acessar qualquer outra rota além de "/usuários" com método GET.
                .build();                                                                                        // Finaliza a configuração do objeto httpSecurity e retorna a instância de SecurityFilterChain configurada de acordo com as políticas definidas
    }


    /*Método responsável para codificar e verificar senhas. Retorna um BCryptPasswordEncoder para codificação de senhas usando o algoritmo 
    BCrypt, que é um algoritmo de hash de senha resistente a ataques de força bruta. Quando um usuário é cadastrado no aplicativo, a senha 
    fornecida pelo usuário é codificada usando o PasswordEncoder e armazenada no banco de dados.*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

/* ANOTAÇÃO:
Só essa configuração já desabilita todas as seguranças q o Spring Security habilitou tanto na autenticação (verificar a identidade de um usuário)
quanto na autorização (determinar as ações que um usuário, dispositivo ou sistema está autorizado a realizar). Logo já é possível acessar a 
requisição "http://localhost:8080/usuarios" sem ser bloqueado pela página de login do Spring Security.
*/