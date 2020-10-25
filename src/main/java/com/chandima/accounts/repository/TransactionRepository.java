package com.chandima.accounts.repository;

import com.chandima.accounts.repository.model.Account;
import com.chandima.accounts.repository.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByAccount(Account account);
}
