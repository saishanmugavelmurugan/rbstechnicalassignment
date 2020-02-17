package com.rbs.utils;


import com.rbs.entity.Transactions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class TransactionUtilsTest {
    @Autowired
    TransactionUtils tu;

    @Test
    void isWithDrawalValid_ok(){
        boolean  result=tu.isWithDrawalValid(Double.parseDouble("100"),Double.parseDouble("200"));
        assertFalse(result);
    }
    @Test
    void isWithDrawalValid_false(){
        boolean  result=tu.isWithDrawalValid(Double.parseDouble("200"),Double.parseDouble("100"));
        assertTrue(result);
    }
    @Test
    void createWithDrawalTransaction(){
        Transactions t =tu.createWithDrawalTransaction("rbs12345",Double.parseDouble("200"));
        assertEquals(t.getType(),"Dr");
    }
    @Test
    void createWithDepositTransaction(){
        Transactions t =tu.createDepositTransaction("rbs12345",Double.parseDouble("200"));
        assertEquals(t.getType(),"Cr");
    }
}
