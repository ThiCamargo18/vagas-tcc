package com.example.mac.security.config;

import com.example.mac.cliente.service.ClienteAutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClienteAutenticacaoService clienteAutenticacaoService;

    private static final String[] LISTA_AUTORIZACAO_CANDIDATO = {
            "/cliente/**",
            "/cadastro/**",
            "/dadosAdicionais/**",
            "/dadosPessoais/**",
            "/candidato/vaga/**",
            "/candidato/entrevista/**",
            "/inicio",
            "/config",
    };

    private static final String[] LISTA_AUTORIZACAO_ADMIN = {
            "/empresa/**",
            "/entrevista/**",
            "/experiencia/**",
            "/habilidades/**",
            "/registro/**",
            "/vaga/**",
            "/admin",
            "/filtro/**",
    };

    private static final String[] LISTA_AUTORIZACAO = {
            "/registrar**",
            "/assets/**",
            "/css/**",
            "/js/**",
            "/scss/**",
            "/vendor/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(LISTA_AUTORIZACAO_ADMIN).hasAuthority("ADMIN")
                .antMatchers(LISTA_AUTORIZACAO_CANDIDATO).hasAnyAuthority("ADMIN", "TL")
                .antMatchers(LISTA_AUTORIZACAO).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(clienteAutenticacaoService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
