package com.ecole221.microsdervices.course.api.gateway.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@AllArgsConstructor
@EnableWebFluxSecurity
@Configuration
public class WebSecurityConfig {


    private final JwtReactorAuthConverter jwtReactorAuthConverter;

    //@Value("${app.keycloak_url_certs}")
    //public final String jwkseturi;


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        serverHttpSecurity.authorizeExchange(exchange ->
                exchange.pathMatchers("/users/**")
                        .permitAll()
                        .pathMatchers("/personne/**").hasAuthority("admin")
                        .pathMatchers("/age/**").hasAuthority("user")
                        .pathMatchers("/keycloak/**").hasAuthority("globaladmin")
                        .anyExchange().authenticated()
        )

        .oauth2ResourceServer(oauth2 -> oauth2
              .jwt(jwt -> jwt
                   .jwtAuthenticationConverter(jwtReactorAuthConverter)
                   //.jwkSetUri(jwkseturi)
                   .jwkSetUri("http://localhost:8080/realms/order-app-realm/protocol/openid-connect/certs")
              )
        );


        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
        SecurityWebFilterChain securityWebFilterChain = serverHttpSecurity.build();
        return securityWebFilterChain;
    }

}