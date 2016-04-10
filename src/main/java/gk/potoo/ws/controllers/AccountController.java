package gk.potoo.ws.controllers;

import gk.potoo.documents.Account;
import gk.potoo.ws.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/accounts")
class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String create(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@RequestBody Account account) {
        return accountService.login(account);
    }

    @RequestMapping("/current")
    public String getUser(Principal user) {
        return user.getName();
    }

}
