package com.apirest.springsecuritydemo2.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apirest.springsecuritydemo2.models.Usuario;
import com.apirest.springsecuritydemo2.repositories.UsuarioRepository;
import com.apirest.springsecuritydemo2.service.AutenticacaoService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter{

    private AutenticacaoService autenticacaoService;
    private UsuarioRepository usuarioRepository;

    /*O método doFilterInternal extrai o token JWT do cabeçalho de autorização HTTP, valida o token e define a autenticação do usuário no 
    contexto de segurança se o token for válido. Se o token for nulo ou inválido, o método simplesmente chama o próximo filtro na cadeia de 
    filtros HTTP. */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = extraiTokenHeader(request);                                                                               // Chama o método extraiTokenHeader para extrair o token JWT do cabeçalho de autorização HTTP.

        if(token != null){                                                                                                       // Verifica se o token não é nulo.
            String login = autenticacaoService.validaTokeJwt(token);                                                             // Chama o método validaTokeJwt da classe AutenticacaoService para validar o token JWT e obter o login do usuário.
            Usuario usuario = usuarioRepository.findByLogin(login);                                                              // Chama o método findByLogin do repositório de usuários para obter o usuário com o login obtido do token JWT.
            var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());    // Cria uma instância de UsernamePasswordAuthenticationToken com o usuário obtido, nulo como senha e as autorizações do usuário.
            SecurityContextHolder.getContext().setAuthentication(autentication);                                                 // Define a autenticação do usuário no contexto de segurança.
        }
        
        filterChain.doFilter(request, response);                                                                                  // Chama o próximo filtro na cadeia de filtros HTTP.
    }


    /*Este método extraiTokenHeader verifica se o cabeçalho de autorização HTTP tem o prefixo "Bearer" e extrai a parte do token JWT do cabeçalho. 
    Se o cabeçalho de autorização for nulo ou não tiver o prefixo "Bearer", o método retorna nulo. */
    public String extraiTokenHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        
        if(authHeader == null){
            return null;
        }

        if(!authHeader.split(" ")[0].equals("Bearer")){ // Verifica se o cabeçalho de autorização tem o prefixo "Bearer" que está na posição zero.
            return null;                                               // Retorna nulo se o cabeçalho de autorização não tiver o prefixo "Bearer".
        }

        return authHeader.split(" ")[1];                         // Retorna a parte do token JWT do cabeçalho de autorização que está na posição um.
    }
}


/* Anotação - Classe OncePerRequestFilter
 * OncePerRequestFilter é uma classe abstrata do Spring Security que fornece uma implementação básica de um filtro HTTP que é executado apenas 
 * uma vez por solicitação. Ele é usado como uma superclasse para filtros HTTP personalizados que precisam ser executados apenas uma vez por 
 * solicitação, evitando assim a repetição desnecessária de operações.
 * 
 * A classe OncePerRequestFilter implementa o método "doFilterInternal" da interface "Filter", que é chamado para processar uma solicitação HTTP. 
 * No entanto, ela também mantém um contador de solicitações por thread, verificando se a solicitação atual já foi processada por um filtro 
 * derivado nesta thread. Se sim, o filtro derivado não é executado novamente.
 * 
 * Para usar a classe OncePerRequestFilter precisa estender a classe e sobrescrever o método "shouldNotFilterAsyncDispatch()" para retornar 
 * "false" se o filtro deve ser executado durante o processamento assíncrono de solicitações. Em seguida pode sobrescrever o método 
 * "doFilterInternal" para implementar a lógica de filtragem personalizada.
 * 
 * Além disso, a classe OncePerRequestFilter fornece um método útil "setFilterProcessesUrl" para definir a URL de processamento de filtros, que 
 * é a URL que o filtro deve processar. Isso é útil quando deseja que o filtro seja executado apenas para solicitações específicas, em vez de 
 * todas as solicitações.
 * 
 * Em resumo, a classe OncePerRequestFilter é uma classe abstrata do Spring Security que fornece uma implementação básica de um filtro HTTP que 
 * é executado apenas uma vez por solicitação. Ela é útil para filtros HTTP personalizados que precisam ser executados apenas uma vez por 
 * solicitação, evitando assim a repetição desnecessária de operações.
 */


/* Anotação: Classe UsernamePasswordAuthenticationToken
 * UsernamePasswordAuthenticationToken é uma classe do Spring Security que representa uma autenticação baseada em nome de usuário e senha. Ele é 
 * usado para representar a autenticação de um usuário em um aplicativo Spring Security.
 * 
 * A classe UsernamePasswordAuthenticationToken implementa a interface "Authentication" do Spring Security e estende a classe 
 * "AbstractAuthenticationToken". Ela armazena o nome de usuário, a senha e as autorizações do usuário.
 * 
 * A classe UsernamePasswordAuthenticationToken tem três construtores:
 * 
 * 1. "UsernamePasswordAuthenticationToken(Object principal, Object credentials)" - Cria uma instância de `UsernamePasswordAuthenticationToken` 
 * com o principal (nome de usuário) e as credenciais (senha).
 * 
 * 2. "UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities)"" - Cria uma 
 * instância de `UsernamePasswordAuthenticationToken` com o principal, as credenciais e as autorizações do usuário.
 * 
 * 3. "UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Map<String, Object> details)"" - 
 * Cria uma instância de `UsernamePasswordAuthenticationToken` com o principal, as credenciais, as autorizações e os detalhes do usuário.
 * 
 * A classe UsernamePasswordAuthenticationToken é usada para representar a autenticação de um usuário em um aplicativo Spring Security. Ela é 
 * usada para armazenar as informações de autenticação de um usuário, como o nome de usuário e a senha, e as autorizações do usuário. Ela é 
 * usada em conjunto com o contexto de segurança do Spring Security para autenticar um usuário em um aplicativo Spring Security.
*/