//package com.anhmt.microservices.jwt;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.util.Strings;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//@Slf4j
//@Component
//public class JWTUtils {
//    public static final String SECRET = "SomeSecretForJWTGeneration";
//
//    public String resolveToken(final String bearerToken) {
//        if (Objects.nonNull(bearerToken)) {
//            return bearerToken.replace(AUTHORIZATION, "").trim();
//        }
//        return null;
//    }
//
//    public Claims validateTokenAndGet(final String token) {
//        if (Strings.isBlank(token)) {
//            return null;
//        }
//
//        try {
//            var parserToken = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//            return parserToken.getBody();
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return null;
//    }
//}
