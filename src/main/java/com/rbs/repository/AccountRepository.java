package com.rbs.repository;

import com.rbs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long > {
    Account findByAccountnumber(String accountNumber);
}
