package gk.potoo.ws.services;

import gk.potoo.config.security.AccountDetailsService;
import gk.potoo.config.security.TokenHandler;
import gk.potoo.documents.Account;
import gk.potoo.exceptions.AccountExistsException;
import gk.potoo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private TokenHandler tokenHandler;

    public String createAccount(Account account) {
        if (accountRepository.findByUsernameOrEmail(account.getUsername(), account.getEmail()) != null) {
            throw new AccountExistsException("Account already exists");
        }

        accountRepository.save(new Account(account.getEmail(), account.getFullName(), account.getPassword(), "ROLE_USER"));
        return login(account);
    }

    public String login(Account loginCredentials) {
        Account account = accountRepository.findByUsernameOrEmail(loginCredentials.getUsername(), loginCredentials.getEmail());
        if (account == null) {
            throw new UsernameNotFoundException("No such user");
        }
        if (!Account.PASSWORD_ENCODER.matches(loginCredentials.getPassword(), account.getPassword())) {
            throw new BadCredentialsException("Username or password is invalid");
        }

        return tokenHandler.createTokenForUser(accountDetailsService.toUser(account));
    }

}
