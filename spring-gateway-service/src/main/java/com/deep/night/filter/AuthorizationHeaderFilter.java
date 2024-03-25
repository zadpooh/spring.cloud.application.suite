package com.deep.night.filter;

import com.deep.night.client.AuthClient;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment env;
    AuthClient authClient;

    public AuthorizationHeaderFilter(Environment env, AuthClient authClient) {
        super(Config.class);
        this.env = env;
        this.authClient= authClient;
    }

    public static class Config {
        // Put configuration properties here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer ", "");

            if (!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };

    }

    // mono, flux -> webflux : 단위 값이면 mono로 반환
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }


    private boolean isJwtValid(String jwtToken) {
        String secretKey = env.getProperty("token.secret");
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        boolean returnValue = true;
        String subject = null;

        try {

            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken).getBody()
                    .getSubject();

            log.info("subject : {}", subject);

        } catch (Exception ex) {
            log.error("jwt error : {}", ex.getMessage());
            returnValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            returnValue = false;
        }

        return returnValue;
    }

    /*private void addAuthorizationHeaders(ServerHttpRequest request, String userId) {
        request.mutate()
                .header("X-Authorization-Id", userId)
                .build();
    }*/

    @Bean
    public ErrorWebExceptionHandler tokenValidation() {
        return new jwtTokenExceptionHandler();
    }

    // 실제 토큰이 null, 만료 등 예외 상황에 따른 예외처리
    public class jwtTokenExceptionHandler implements ErrorWebExceptionHandler {
        private Map getErrorCode(int errorCode) {
            //return "{ errorCode : " + errorCode + "}";
            return Map.of("errorCode", errorCode);
        }

        @Override
        public Mono<Void> handle(
                ServerWebExchange exchange, Throwable ex) {
            int errorCode = 500;
            if (ex.getClass() == NullPointerException.class) {
                errorCode = 100;
            } else if (ex.getClass() == ExpiredJwtException.class) {
                errorCode = 200;
            }

            byte[] bytes = getErrorCode(errorCode).toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
    }

}
