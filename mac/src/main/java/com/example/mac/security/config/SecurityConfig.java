package com.example.mac.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

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
            "/h2",
    };

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers(LISTA_AUTORIZACAO_ADMIN).hasAuthority("ADMIN")
//                    .antMatchers(LISTA_AUTORIZACAO_CANDIDATO).hasAnyAuthority("ADMIN", "TL")
//                    .antMatchers(LISTA_AUTORIZACAO).permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .successForwardUrl("/")
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(LISTA_AUTORIZACAO_ADMIN).hasAuthority("ADMIN")
                    .antMatchers(LISTA_AUTORIZACAO_CANDIDATO).hasAnyAuthority("ADMIN", "CANDIDATO")
                    .antMatchers(LISTA_AUTORIZACAO).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                .and().
                    cors().and().
                    csrf().disable();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
