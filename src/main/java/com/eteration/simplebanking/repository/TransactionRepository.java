package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.GetTransactionsResponse;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<GetTransactionsResponse, Long> {

    @Query(value="SELECT * FROM transactions t where t.account_id = 1",nativeQuery = true)
    public List<GetTransactionsResponse> getTransactions(Long accountId);
}
