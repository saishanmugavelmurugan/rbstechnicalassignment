package com.rbs.service;

import com.rbs.RBSException.AccountNumberNotFoundException;
import com.rbs.RBSException.InsufficientFundException;
import com.rbs.entity.Account;
import com.rbs.entity.Transactions;
import com.rbs.pojo.RestApiResponse;
import com.rbs.repository.AccountRepository;
import com.rbs.repository.AccountRepositoryTest;
import com.rbs.repository.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {
    @MockBean
    AccountRepository ar;
    @MockBean
    TransactionRepository tr;
    @Autowired
    AccountService as;
    @Test
    @DisplayName("Get account details with balance")
    void getBalance(){
        given(ar.findByAccountnumber(anyString())).willReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        Account account=as.getBalance("rbs12345");
        assertNotNull(account);
    }
    @Test
    @DisplayName("account number not found")
    void getBalance_account_not_found(){
        given(ar.findByAccountnumber(anyString())).willReturn(null);
        Exception exception = assertThrows(
                AccountNumberNotFoundException.class,
                () -> as.getBalance("rbs12345"));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("Account Not Found :rbs12345"));
    }
    @Test
    @DisplayName("withdrawalAmount_ok")
    void withdrawalAmount_ok(){
        given(ar.findByAccountnumber(anyString())).willReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        RestApiResponse rar=as.withdrawalAmount(Double.parseDouble("10"),"rbs12345");
        assertNotNull(rar);
    }
    @Test
    @DisplayName("withdrawal_Amount_fund_not available")
    void withdrawalAmount_fund_not_available(){
        given(ar.findByAccountnumber(anyString())).willReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        Exception exception = assertThrows(
                InsufficientFundException.class,
                () -> as.withdrawalAmount(Double.parseDouble("2000"),"rbs12345"));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("Insufficient fund"));
    }
    @Test
    @DisplayName("deposit_ok")
    void deposit_ok(){
        given(ar.findByAccountnumber(anyString())).willReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        RestApiResponse rar=as.depositAmount(Double.parseDouble("10"),"rbs12345");
        assertNotNull(rar);
    }
    @Test
    @DisplayName("deposit account not available")
    void deposit_account_not_available(){
        given(ar.findByAccountnumber(anyString())).willReturn(null);
        Exception exception = assertThrows(
                AccountNumberNotFoundException.class,
                () -> as.depositAmount(Double.parseDouble("10"),"rbs12345"));
        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("Account number not found"));
    }
    @Test
    @DisplayName("debit list transaction")
    void debitListTransaction(){
        List<Transactions> drList=new ArrayList<Transactions>();
        drList.add(new Transactions(Long.parseLong("1"),"rbs12345","Dr","withhDrawal",Double.parseDouble("10")));
        drList.add(new Transactions(Long.parseLong("2"),"rbs12345","Dr","withhDrawal",Double.parseDouble("10")));
        given(tr.findByAccountnumberAndType(anyString(),anyString())).willReturn(drList);
        List<Transactions> transactions=as.getCreditOrDebitList("Dr","rbs12345");
        assertEquals(transactions.size(),2);
    }
    @Test
    @DisplayName("credit list transaction")
    void creditListTransaction(){
        List<Transactions> drList=new ArrayList<Transactions>();
        drList.add(new Transactions(Long.parseLong("1"),"rbs12345","Cr","withhDrawal",Double.parseDouble("10")));
        drList.add(new Transactions(Long.parseLong("2"),"rbs12345","Cr","withhDrawal",Double.parseDouble("10")));
        given(tr.findByAccountnumberAndType(anyString(),anyString())).willReturn(drList);
        List<Transactions> transactions=as.getCreditOrDebitList("Cr","rbs12345");
        assertEquals(transactions.size(),2);
    }
    @Test
    @DisplayName("add Account")
    void addAccount(){
        given(ar.save(new Account("rbs12345",Double.parseDouble("100")))).willReturn(null);
        RestApiResponse rest=as.createAccount("rbs12345",Double.parseDouble("100"));
        assertNotNull(rest);
    }
}
