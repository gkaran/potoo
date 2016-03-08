package gk.potoo.ws.controllers;

import gk.potoo.documents.Account;
import gk.potoo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public Account create(@RequestBody Account account) {
        return accountRepository.save(new Account(account.getUsername(), account.getPassword(), "ROLE_USER"));
    }


}
