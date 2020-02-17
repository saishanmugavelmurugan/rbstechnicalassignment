package com.rbs.utils;

import com.rbs.entity.Transactions;
import org.springframework.stereotype.Service;

@Service
public class TransactionUtils {
    /**
     *
     * @param sourceAmount
     * @param withdrawalAmount
     * @return
     */
    public boolean isWithDrawalValid(Double sourceAmount,Double withdrawalAmount){
        if(sourceAmount>withdrawalAmount){
            return true;
        }
        return false;
    }

    /**
     *
     * @param accountNumber
     * @param amount
     * @return Transactions
     */
    public Transactions createWithDrawalTransaction(String accountNumber,Double amount){
        return new Transactions(accountNumber,"Dr","withdrawal",amount);
    }

    /**
     *
     * @param accountNumber
     * @param amount
     * @return Transactions
     */
    public Transactions createDepositTransaction(String accountNumber,Double amount){
        return new Transactions(accountNumber,"Cr","deposit",amount);
    }
}
