//package com.anhmt.microservices.fillter;
//
//import com.anhmt.microservices.jwt.JWTUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.function.Predicate;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class APIGatewayFilter implements GatewayFilter {
//    private final JWTUtils jwtUtils;
//    private final List<String> apisIgnore = Arrays.asList("/api/auth/login/", "/api/logout/", "/api/refresh-token/");
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        var request = exchange.getRequest();
//        var response = exchange.getResponse();
//        String requestUrl = request.getURI().getPath();
//
//        log.info("Request URL #{}", requestUrl);
//
//        if (!ignoreRequest(requestUrl)) {
//            log.info("Check token");
//
//            if (!request.getHeaders().containsKey(AUTHORIZATION)) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                return response.setComplete();
//            }
//
//            String token = jwtUtils.resolveToken(request.getHeaders().getFirst(AUTHORIZATION));
//            var claims = jwtUtils.validateTokenAndGet(token);
//
//            if (Objects.isNull(claims)) {
//                response.setStatusCode(HttpStatus.BAD_REQUEST);
//                return response.setComplete();
//            }
//        }
//
//        return chain.filter(exchange);
//    }
//
//    private boolean ignoreRequest(final String url) {
//        Predicate predicate = r -> apisIgnore.contains(r);
//
//        return predicate.test(url);
//    }
//}
