package com.chandima.accounts.repository;

import com.chandima.accounts.repository.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> getByCustomerId(String customerId);
    Account getByCustomerIdAndAccountNumber(String customerId, String accountNumber);
}
