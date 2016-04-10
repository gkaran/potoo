package gk.potoo.config.security;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenHandler {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private AccountDetailsService accountDetailsService;

    Optional<User> parseUserFromToken(String token) {
        try {
            String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
            return Optional.ofNullable((User)accountDetailsService.loadUserByUsername(username));
        } catch (ExpiredJwtException|UnsupportedJwtException|MalformedJwtException|SignatureException|IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
            .setSubject(user.getUsername())
            .setExpiration(new DateTime().plusHours(8).toDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

}
