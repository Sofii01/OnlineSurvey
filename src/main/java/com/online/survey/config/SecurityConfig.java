package com.online.survey.config;


import com.online.survey.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    RequestMatcher publicUrls =new OrRequestMatcher(
            new AntPathRequestMatcher("/auth/**"),
            new AntPathRequestMatcher("/users/**"),
            new AntPathRequestMatcher("/survey/**"),
            new AntPathRequestMatcher("/creator/**")
    );
    RequestMatcher admiUrls = new OrRequestMatcher(
            new AntPathRequestMatcher("/admi/")

    );

    RequestMatcher creatorUrls = new OrRequestMatcher(
            new AntPathRequestMatcher("/admi")
    );
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->{
                    authRequest.requestMatchers(publicUrls).permitAll();
                    authRequest.requestMatchers(admiUrls).hasAuthority("Administrator");
                    authRequest.requestMatchers(creatorUrls).hasAuthority("Creator");
                    authRequest.anyRequest().authenticated();
                })
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

}
