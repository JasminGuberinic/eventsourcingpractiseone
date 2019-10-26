package com.example.accountservice;

import com.example.account.model.Account;
import com.example.account.model.AccountEvent;
import com.example.account.model.CreateAccount;
import com.example.repo.AccountEventRepository;
import com.example.repo.AccountRepository;
import com.example.staus.AccountStatus;
import com.example.types.AccoutEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class AccountController {
    Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountEventRepository accountEventRepository;

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Integer id){
        return accountRepository.getOne(id);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody CreateAccount createAccount){
        Account account = createAccount.getAccount();
        Account newAccount = new Account();
        try {
            newAccount = accountRepository.save(account);
            accountEventRepository.save(new AccountEvent(account.getAccountNum(), AccoutEventType.PENDING));
        } catch (Exception exception) {
           String message = exception.getMessage();
           logger.info("An ERROR Message" + message);
        }

        return newAccount;
    }

    @PostMapping("/accounts/{id}/activate")
    public Account activeAccount(@PathVariable Integer id){
        Account account = accountRepository.getOne(id);
        AccountStatus status = account.getStatus();

        if(status == AccountStatus.PENDING){
            account.setStatus(AccountStatus.ACTIVE);
            accountEventRepository.save(new AccountEvent(account.getAccountNum(), AccoutEventType.ACTIVE));
        }

        return account;
    }

}
