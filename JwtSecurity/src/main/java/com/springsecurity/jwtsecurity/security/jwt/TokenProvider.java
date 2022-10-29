package com.springsecurity.jwtsecurity.security.jwt;

import com.springsecurity.jwtsecurity.dto.response.auth.TokenRes;
import com.springsecurity.jwtsecurity.security.DomainUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecurity.jwtsecurity.security.jwt.JwtProperties.EXPIRATION_TIME;
import static com.springsecurity.jwtsecurity.security.jwt.JwtProperties.HEADER_STRING;
import static com.springsecurity.jwtsecurity.security.jwt.JwtProperties.SECRET;
import static com.springsecurity.jwtsecurity.security.jwt.JwtProperties.TOKEN_PREFIX;
import static io.jsonwebtoken.Header.JWT_TYPE;
import static io.jsonwebtoken.Header.TYPE;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Slf4j
@AllArgsConstructor
@Component
public class TokenProvider {

    private static final String USER_ID_KEY = "user_id";
    private static final String USERNAME_KEY = "username";
    private static final String ROLE_KEY = "roles";
    private static final String PERMISSION_KEY = "permissions";
//    private JwtParser jwtParser;

    public TokenRes generateToken(final DomainUserDetails domainUserDetails) {
        Collection<? extends GrantedAuthority> roles = domainUserDetails.getRoles();
        Collection<? extends GrantedAuthority> permissions = domainUserDetails.getAuthorities();

        Set<String> roleSet = toSetString(roles);
        Set<String> permissionSet = toSetString(permissions);

        Claims claims = Jwts.claims();
        claims.put(USER_ID_KEY, domainUserDetails.getId());
        claims.put(USERNAME_KEY, domainUserDetails.getUsername());
        claims.put(ROLE_KEY, String.join(",", roleSet));
        claims.put(PERMISSION_KEY, String.join(",", permissionSet));

        String token = Jwts.builder()
                           .setHeaderParam(TYPE, JWT_TYPE)
//                           .setId(domainUserDetails.getId().toString())
//                           .setSubject(domainUserDetails.getUsername())
                           .setClaims(claims)
                           .setIssuedAt(new Date())
                           .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                           .signWith(HS256, SECRET)
                           .compact();

        return TokenRes.builder()
                       .accessToken(token)
                       .tokenType(TOKEN_PREFIX)
                       .expireIn(EXPIRATION_TIME)
                       .roles(roleSet)
                       .permissions(permissionSet)
                       .build();
    }

    public String resolveToken(final HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(HEADER_STRING);

        if (Objects.nonNull(bearerToken)) {
            return bearerToken.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

    public Claims validateTokenAndGet(final String token) {
        if (Strings.isBlank(token)) {
            return null;
        }

        try {
            var parserToken = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return parserToken.getBody();
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return null;
    }

    public Authentication getAuthentication(final Claims claims, final String token) {
        Set<String> roleSet = Arrays.stream(claims.get(ROLE_KEY).toString().split(","))
                                    .collect(Collectors.toSet());

        Set<String> permissionSet = Arrays.stream(claims.get(PERMISSION_KEY).toString().split(","))
                                          .collect(Collectors.toSet());

        Collection<SimpleGrantedAuthority> roles = new HashSet<>(roleSet.size());
        Collection<SimpleGrantedAuthority> permissions = new HashSet<>(permissionSet.size());

        roleSet.forEach(role -> roles.add(new SimpleGrantedAuthority(role.toUpperCase())));
        permissionSet.forEach(permission -> permissions.add(new SimpleGrantedAuthority(permission.toUpperCase())));

        Long userId = Long.parseLong(claims.get(USER_ID_KEY).toString());
        String username = claims.get(USERNAME_KEY).toString();

        DomainUserDetails domainUserDetails = DomainUserDetails.builder()
                                                               .id(userId)
                                                               .username(username)
                                                               .roles(roles)
                                                               .authorities(permissions)
                                                               .build();

        return new UsernamePasswordAuthenticationToken(domainUserDetails, token, collectRolesAndPermissions(roles, permissions));
    }

    public Set<String> toSetString(final Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                          .map(role -> new SimpleGrantedAuthority(role.getAuthority()).getAuthority())
                          .collect(Collectors.toSet());
    }

    private Collection<SimpleGrantedAuthority> collectRolesAndPermissions(final Collection<SimpleGrantedAuthority> roles,
                                                                          final Collection<SimpleGrantedAuthority> permissions) {
        Collection<SimpleGrantedAuthority> roleAndPermission = new HashSet<>(roles.size() + permissions.size());
        roleAndPermission.addAll(roles);
        roleAndPermission.addAll(permissions);

        return roleAndPermission;
    }
}
