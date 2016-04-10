package gk.potoo.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsernameAuditorAwareTest {

    private static final String USERNAME = "username";
    @Mock
    Authentication authentication;

    @Mock
    SecurityContext securityContext;

    @Mock
    User user;

    private static final UsernameAuditorAware auditor = new UsernameAuditorAware();

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testAuthenticationNull() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(null);
        assertEquals(auditor.getCurrentAuditor(), null);
    }

    @Test
    public void testAuthenticationIsNotAuthenticated() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);
        assertEquals(auditor.getCurrentAuditor(), null);

    }

    @Test
    public void testAuthenticatedUser() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(user);
        when(user.getUsername()).thenReturn(USERNAME);
        assertEquals(auditor.getCurrentAuditor(), USERNAME);
    }
}