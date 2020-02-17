package com.rbs.repository;

import com.rbs.entity.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends CrudRepository<Transactions, Long> {
    List<Transactions> findByAccountnumber(String accountnumber);
    @Query("select new Transactions(id,accountnumber,type, method, amount) from Transactions t where t.type = ?1 and t.accountnumber = ?2")
    List<Transactions> findByAccountnumberAndType(String type,String accountnumber);
}
