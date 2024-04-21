package com.apirest.springsecuritydemo2.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apirest.springsecuritydemo2.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Role role;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // Implementando interface UserDetails do Spring Security
    
    /* Este método é responsável por retornar uma coleção de autoridades (papéis) associadas ao usuário. No caso, se o usuário tiver a função 
     * de Role ADMIN, ele terá as autoridades "ROLE_ADMIN" e "ROLE_USER". Caso contrário, apenas a autoridade "ROLE_USER" será retornada.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),  // SimpleGrantedAuthority é uma classe do Spring Security que representa uma autoridade concedida a um usuário. Ela é usada para definir os papéis (roles) que um usuário possui no sistema.
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
