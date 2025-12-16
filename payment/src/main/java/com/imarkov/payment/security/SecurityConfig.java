package com.imarkov.payment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/payment/**")
                            .hasAnyAuthority("SCOPE_service-b.read")
                            .anyRequest().authenticated();
                })
                .oauth2ResourceServer(oath2 -> oath2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
