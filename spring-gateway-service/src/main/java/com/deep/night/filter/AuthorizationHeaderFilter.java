package com.deep.night.filter;

import com.deep.night.dto.AuthDto;
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
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Map;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {
        // Put configuration properties here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)
                    && !request.getCookies().containsKey("refreshToken")) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            if(request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                String authorizationHeader =  request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String jwt = authorizationHeader.replace("Bearer ", "");

                if (!isJwtValid(jwt)) {
                    return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
                }
            }

            if(request.getCookies().containsKey("refreshToken")){
                WebClient webClient = WebClient.builder()
                        .baseUrl("http://localhost:7733")
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build();

                try {

                    AuthDto.SignUpRes signUpRes =  webClient.post().uri("/api/auth/generate")
                            .header("refreshToken", request.getCookies().get("refreshToken").get(0).getValue())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(AuthDto.SignUpRes.class)
                            .block();

                    if (!isJwtValid(signUpRes.getAccessToken())) {
                        return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
                    }

                } catch (Exception e){
                    return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED, e);
                }
            }
            return chain.filter(exchange);
        };

    }

    // mono, flux -> webflux : 단위 값이면 mono로 반환
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        return onError(exchange, err, httpStatus,null);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err+ " : {} ", ex);
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

            ex.printStackTrace();

            byte[] bytes = getErrorCode(errorCode).toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
    }

}
