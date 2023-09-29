package com.thanksen.osahaneat.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {

    private CustomUserDetailService customUserDetailService;
    private CustomJwtFilter customJwtFilter;

    @Autowired
    public CustomFilterSecurity(CustomUserDetailService customUserDetailService, CustomJwtFilter customJwtFilter) {
        this.customUserDetailService = customUserDetailService;
        this.customJwtFilter = customJwtFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/users/**").authenticated()
                                .requestMatchers("/file/**").permitAll()
                                .requestMatchers("/restaurant/**").permitAll()
                                .requestMatchers("/menu/**").permitAll()
                                .requestMatchers("/category/**").permitAll()
                                .requestMatchers("/order/**").permitAll()
                ).addFilterBefore(customJwtFilter, AuthorizationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}