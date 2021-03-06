package gk.potoo.config;

import gk.potoo.config.security.AccountDetailsService;
import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class AccountDetailsServiceTest {

    private static final String EMAIL = "test@test.test";
    private static final String PASSWORD = "123123";
    private static final String[] ROLES = {"ROLE_USER"};
    private static final String FULLNAME = "John Doe";
    private static final String USERNAME = "takis";

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountDetailsService accountDetailsService;

    private Account account = new Account(EMAIL, FULLNAME, PASSWORD, ROLES);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        account.setUsername(USERNAME);
        when(repository.findByUsername(USERNAME)).thenReturn(account);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        List rolesList = asList(ROLES);
        assertThat(accountDetailsService.loadUserByUsername(USERNAME))
            .isInstanceOf(User.class)
            .hasFieldOrPropertyWithValue("username", USERNAME)
            .hasFieldOrPropertyWithValue("password", PASSWORD)
            .matches(userDetails -> !userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !rolesList.contains(authority))
                .findAny()
                .isPresent()
            );
    }

    @Test()
    public void testLoadUserByUsernameDoesNotExist() {
        assertThatThrownBy(() -> accountDetailsService.loadUserByUsername("non existing user"))
            .isInstanceOf(UsernameNotFoundException.class)
            .hasMessage("No such user 'non existing user'")
        ;
    }
}