package com.rbs.repository;

import com.rbs.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.mockito.BDDMockito.given;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository ar;
    @Autowired
    private TestEntityManager  entityManager;

    @Test
    @DisplayName("account details")
    void getAccountDetails(){
        //given(ar.findByAccountnumber("rbs12345")).willReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
       // Account account =entityManager.persistAndFlush(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        //Account repoAccount=ar.findByAccountnumber("rbs12345");

    }
}
