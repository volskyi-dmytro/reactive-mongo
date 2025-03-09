package com.stpunk47.reactive_mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SpringSecConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity httpSecurity) {

        httpSecurity
                .csrf((ServerHttpSecurity.CsrfSpec::disable))
                .authorizeExchange(authorize ->
                        authorize.anyExchange().authenticated())
                .oauth2ResourceServer(resourceServer ->
                        resourceServer.jwt(Customizer.withDefaults()))
        ;
        return httpSecurity.build();

    }
}
