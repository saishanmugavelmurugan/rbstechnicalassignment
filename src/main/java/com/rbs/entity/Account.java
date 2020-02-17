package com.rbs.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account implements Serializable {
    @Id @GeneratedValue
    @Column(name="id",insertable = true, updatable = false)
    Long id;
    @Column(name="accountnumber",insertable = true, updatable = false,unique = true)
    String accountnumber;
    @Column(name="balance",insertable = true, updatable = true)
    Double balance;

    public Account(String accountnumber, Double balance) {
        this.accountnumber = accountnumber;
        this.balance = balance;
    }

    public Account(Long id, String accountnumber, Double balance) {
        this.id = id;
        this.accountnumber = accountnumber;
        this.balance = balance;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountnumber='" + accountnumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
