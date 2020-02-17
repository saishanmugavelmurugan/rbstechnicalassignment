package com.rbs.api;

import com.rbs.pojo.Transaction;
import com.rbs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService as;

    /**
     *
     * @param accountNumber
     * @return
     */
    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber){
        return new ResponseEntity<>(as.getBalance(accountNumber), HttpStatus.OK);
    }

    /**
     *
     * @param amount
     * @return
     */
    @PostMapping("/withDrawal")
    public ResponseEntity<?> withdrawalAmount(@RequestBody Transaction transaction){
        return new ResponseEntity<>(as.withdrawalAmount(transaction.getAmount(),transaction.getAccountNumber()), HttpStatus.OK);
    }

    /**
     *
     * @param amount
     * @return
     */
    @PostMapping("/deposit")
    public ResponseEntity<?> depositAmount(@RequestBody Transaction transaction){
        return new ResponseEntity<>(as.depositAmount(transaction.getAmount(),transaction.getAccountNumber()), HttpStatus.OK);
    }

    /**
     *
     * @param type
     * @param accountNumber
     * @return ResponseEntity
     */
    @GetMapping("/transactions/{type}/{accountNumber}")
    public ResponseEntity<?> getCreditDebitBalance(@PathVariable String type,@PathVariable String accountNumber){
        return new ResponseEntity<>(as.getCreditOrDebitList(type,accountNumber), HttpStatus.OK);
    }

    /**
     *
     * @param accountNumber
     * @param amount
     * @return ResponseEntity
     */
    @GetMapping("/addAccount/{accountNumber}/{amount}")
    public ResponseEntity<?> addAccount(@PathVariable String accountNumber,@PathVariable Double amount){
        return new ResponseEntity<>(as.createAccount(accountNumber,amount), HttpStatus.OK);
    }

}
