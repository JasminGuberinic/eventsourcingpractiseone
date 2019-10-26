package com.example.account.model;

import com.example.types.AccoutEventType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class AccountEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String accountNumber;

    private AccoutEventType accountEventType = AccoutEventType.PENDING;

    private Long timestamp;


    public AccountEvent() {
        timestamp = new Date().getTime();
    }

    public AccountEvent(AccoutEventType eventType) {
        accountEventType = eventType;
    }

    public AccountEvent(String accountNum, AccoutEventType eventType) {
        accountNumber = accountNum;
        accountEventType = eventType;
    }
}
