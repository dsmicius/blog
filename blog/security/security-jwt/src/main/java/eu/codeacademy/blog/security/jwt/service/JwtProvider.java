package eu.codeacademy.blog.security.jwt.service;

import eu.codeacademy.blog.security.jwt.dto.UserRoleDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final static Date NOW = new Date();

    @Value("#{${security.jwt.validity-time} * 60 * 1000}")
    private long tokenValidityInMillis;

    @Value("${security.jwt.secret-key}")
    private byte[] secretKey;

    public String getToken(UserRoleDto principal) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("blog-api")
                .setAudience("blog-ui")
                .setSubject(principal.getUsername())
                .setIssuedAt(NOW)
                .setExpiration(new Date(NOW.getTime() + tokenValidityInMillis))
                .claim("roles", principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet()))
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .compact();
    }
}