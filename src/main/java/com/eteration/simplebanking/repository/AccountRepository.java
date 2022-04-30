package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value="SELECT * FROM account a where a.account_number = :accountNumber",nativeQuery = true)
    public Account findByAccountNumber(@Param("accountNumber") String accountNumber);

}
