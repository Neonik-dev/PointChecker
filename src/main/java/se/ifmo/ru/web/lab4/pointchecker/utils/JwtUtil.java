package se.ifmo.ru.web.lab4.pointchecker.utils;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import se.ifmo.ru.web.lab4.pointchecker.configuration.JwtConfig;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@EnableConfigurationProperties(JwtConfig.class)
public class JwtUtil {
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtConfig jwtConfig;
    private final JwtParser jwtParser;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.jwtParser = Jwts.parser().setSigningKey(jwtConfig.secret());
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("email", user.getEmail());
        Date tokenValidity = new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(jwtConfig.exp()));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.secret())
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            return token == null ? null : parseJwtClaims(token);
        } catch (ExpiredJwtException e) {
            req.setAttribute("expired", e.getMessage());
            throw e;
        } catch (Exception e) {
            req.setAttribute("invalid", e.getMessage());
            throw e;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        return claims.getExpiration().after(new Date());
    }
}
