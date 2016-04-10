package gk.potoo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class UsernameAuditorAware implements AuditorAware<String> {

    private static final String ANONYMOUS_USERNAME = "anonymous";
    private static final String TESTGUY_USERNAME = "testguy";

    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return TESTGUY_USERNAME;
        }

        if (authentication.getPrincipal().equals("anonymousUser")) {
            return ANONYMOUS_USERNAME;
        }

        return (String)authentication.getPrincipal();
    }
}