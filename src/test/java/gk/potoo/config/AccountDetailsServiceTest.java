package gk.potoo.config;

import gk.potoo.PotooApplication;
import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PotooApplication.class)
public class AccountDetailsServiceTest {

    private static final String USERNAME = "takis";
    private static final String PASSWORD = "123123";
    private static final String[] ROLES = {"ROLE_USER"};
    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountDetailsService accountDetailsService;

    private Account account = new Account(USERNAME, PASSWORD, ROLES);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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