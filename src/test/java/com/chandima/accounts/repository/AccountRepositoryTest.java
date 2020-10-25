package com.chandima.accounts.repository;


import com.chandima.accounts.repository.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * only queries are really tested in the tests beause save is provided by the framework.
 * but saving also is tested indirectly.
 */
@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void getByCustomerId_whenThereAreAccounts_returnsListSuccessfully() {
        accountRepository.save(AccountFixture.createAccount("001", 10, AccountType.Current, Currency.AUD));
        accountRepository.save(AccountFixture.createAccount("001", 20, AccountType.Saving, Currency.AUD));
        accountRepository.save(AccountFixture.createAccount("001", 12, AccountType.Current, Currency.EUR));
        accountRepository.save(AccountFixture.createAccount("001", 16, AccountType.Current, Currency.AUD));
        accountRepository.save(AccountFixture.createAccount("002", 15, AccountType.Saving, Currency.AUD));
        accountRepository.save(AccountFixture.createAccount("003", 17, AccountType.Current, Currency.AUD));

        List<Account> accounts = accountRepository.getByCustomerId("001");
        List<Account> all = accountRepository.findAll();
        assertEquals(6, all.size());
        assertEquals(4, accounts.size());
    }

    @Test
    public void getByCustomerIdAndAccountId_whenBothMatching_returnsMatchingAccount() {
        Account account1 = accountRepository.save(AccountFixture.createAccount("001", 10, AccountType.Current, Currency.AUD));
        Account matching1 = accountRepository.getByCustomerIdAndAccountNumber("001", account1.getAccountNumber());
        assertEquals(account1, matching1);
    }
    @Test
    public void getByCustomerIdAndAccountId_whenCustomerIdNotMatching_returnsNull() {
        Account account1 = accountRepository.save(AccountFixture.createAccount("001", 10, AccountType.Current, Currency.AUD));
        Account notMatching1 = accountRepository.getByCustomerIdAndAccountNumber("002", account1.getAccountNumber());
        assertNull(notMatching1);
    }


}
