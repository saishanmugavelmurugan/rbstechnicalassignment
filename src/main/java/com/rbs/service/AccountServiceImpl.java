package com.rbs.service;

import com.rbs.RBSException.AccountNumberNotFoundException;
import com.rbs.RBSException.InsufficientFundException;
import com.rbs.entity.Account;
import com.rbs.entity.Transactions;
import com.rbs.pojo.RestApiResponse;
import com.rbs.repository.AccountRepository;
import com.rbs.repository.TransactionRepository;
import com.rbs.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository ar;
    @Autowired
    TransactionRepository tr;
    @Autowired
    TransactionUtils tu;


    /**
     * Return balance for the given account
     * @param accountNumber
     * @return Account
     */
    @Override
    public Account getBalance(String accountNumber) {
        Account account=ar.findByAccountnumber(accountNumber);
        if(account!=null){
            return account;
        }else{
            throw  new AccountNumberNotFoundException("Account Not Found :"+accountNumber);
        }
    }

    /**
     *
     * @param amount
     * @param accountNumber
     * @return
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public RestApiResponse withdrawalAmount(Double amount, String accountNumber) {
        Account account =ar.findByAccountnumber(accountNumber);
        if(account!=null){
            if(tu.isWithDrawalValid(account.getBalance(),amount)){
                account.setBalance(account.getBalance()-amount);
                ar.save(account);
                tr.save(tu.createWithDrawalTransaction(accountNumber,amount));
            }else{
                throw new InsufficientFundException("Insufficient fund");
            }
        }else{
            throw new AccountNumberNotFoundException("Account number not found");
        }
        return new RestApiResponse(HttpStatus.CREATED,"201","success","WithdrawlTransaction successfull");
    }

    /**
     *
     * @param amount
     * @param accountNumber
     * @return RestApiResponse
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public RestApiResponse depositAmount(Double amount, String accountNumber) {
        Account account =ar.findByAccountnumber(accountNumber);
        if(account!=null){
                account.setBalance(account.getBalance()+amount);
                ar.save(account);
                tr.save(tu.createDepositTransaction(accountNumber,amount));
        }else{
            throw new AccountNumberNotFoundException("Account number not found");
        }
        return new RestApiResponse(HttpStatus.CREATED,"201","success","deposit  successfull");
    }

    /**
     *
     * @param type
     * @param accountNumber
     * @return List<Transactions>
     */
    @Override
    public List<Transactions> getCreditOrDebitList(String accountNumber,String type) {
        return tr.findByAccountnumberAndType(accountNumber, type);
    }

    /**
     *
     * @param accountNumber
     * @param amount
     * @return RestApiResponse
     */
    @Override
    public RestApiResponse createAccount(String accountNumber,Double amount) {
        Account account=new Account(accountNumber,amount);
        ar.save(account);
        return new RestApiResponse(HttpStatus.CREATED,"201","success","Account created successfully");
    }
}


