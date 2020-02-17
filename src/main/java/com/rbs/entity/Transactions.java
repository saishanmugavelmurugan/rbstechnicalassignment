package com.rbs.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Transactions implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id",insertable = true, updatable = true)
    Long id;
    @NotNull
    @Column(name="accountnumber",insertable = true, updatable = false)
    String accountnumber;
    @Column(name="type",insertable = true, updatable = true)
    String type;
    @Column(name="method",insertable = true, updatable = true)
    String method;
    @Column(name = "amount",insertable = true, updatable = true)
    Double amount;

    public Transactions() {
    }

    public Transactions(Long id, @NotNull String accountnumber, String type, String method, Double amount) {
        this.id = id;
        this.accountnumber = accountnumber;
        this.type = type;
        this.method = method;
        this.amount = amount;
    }

    public Transactions(String accountnumber, String type, String method, Double amount) {
        this.accountnumber = accountnumber;
        this.type = type;
        this.method = method;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountnumber() { return accountnumber;    }

    public void setAccountnumber(String accountnumber) {this.accountnumber = accountnumber;    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", accountnumber='" + accountnumber + '\'' +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
