package gk.potoo.config.security;

import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user '" + username + "'");
        }
        return toUser(account);
    }

    public User toUser(Account account) {
        return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList(account.getRoles()));
    }


}
