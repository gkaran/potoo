package gk.potoo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
class TokenAuthenticationService {

    private static final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    private TokenHandler tokenHandler;

    Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {

            final Optional<User> user =  tokenHandler.parseUserFromToken(token.replace("Bearer ", ""));
            if (user.isPresent()) {
                return new UserAuthentication(user.get());
            }
        }
        return new AnonymousAuthenticationToken(ANONYMOUS_USER, ANONYMOUS_USER, createAuthorityList("ROLE_ANONYMOUS"));
    }

}
