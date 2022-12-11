package com.dh.trabalhoIntegrador.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hibernate.criterion.Restrictions.and;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/paciente/buscarTodos").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());

    }
}
