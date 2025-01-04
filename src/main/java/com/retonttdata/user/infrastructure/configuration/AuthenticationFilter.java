package com.retonttdata.user.infrastructure.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Collections;


@Component
public class AuthenticationFilter implements WebFilter {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                DecodedJWT jwt = validateToken(token);
                String username = jwt.getSubject();

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));

            } catch (Exception e) {
                return Mono.error(new RuntimeException("Invalid JWT token"));
            }
        }

        return chain.filter(exchange);
    }

    private DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .build()
                .verify(token);
    }
}