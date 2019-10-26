package com.example.account.model;

import com.example.account.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccount {
    private Account account;
    public void setAccount(Account account) {
        this.account = account;
    }
}
