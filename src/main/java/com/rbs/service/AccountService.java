package com.rbs.service;

import com.rbs.entity.Account;
import com.rbs.entity.Transactions;
import com.rbs.pojo.RestApiResponse;

import java.util.List;

public interface AccountService {
    Account getBalance(String account);
    RestApiResponse withdrawalAmount(Double amount, String accountNumber);
    RestApiResponse depositAmount(Double amount,String accountNumber);
    List<Transactions> getCreditOrDebitList(String type, String accountNumber);
    RestApiResponse createAccount(String accountNumber,Double amount);
}
