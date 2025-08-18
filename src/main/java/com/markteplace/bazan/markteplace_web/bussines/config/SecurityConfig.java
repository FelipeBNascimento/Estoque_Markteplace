package com.markteplace.bazan.markteplace_web.bussines.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para simplificar em APIs REST. Cuidado em apps com HTML forms.
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Apenas o GET para /produtos (mostrar estoque) requer autenticação
                                .requestMatchers(HttpMethod.GET, "/produtos").authenticated()
                                //.requestMatchers(HttpMethod.POST, "/produtos").authenticated()
                                .anyRequest().permitAll() // Todas as outras requisições (POST, PUT, DELETE, GET por ID) são permitidas sem autenticação
                )
                .formLogin(formLogin ->
                        formLogin
                                .permitAll()
                )
                .httpBasic(httpBasic -> {}); // Habilita autenticação HTTP Basic para APIs

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Criando um usuário em memória para exemplo.
        // Em produção, você DEVE integrar com um banco de dados ou serviço de persistência.
        UserDetails user = User.builder()
                .username("admin") // Seu nome de usuário
                .password(passwordEncoder.encode("1234")) // Sua senha, codificada. Mude para uma senha forte!
                .roles("USER", "ADMIN") // Papéis do usuário
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usa BCrypt para codificar senhas. É uma prática segura.
        return new BCryptPasswordEncoder();
    }


}





