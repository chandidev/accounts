package com.chandima.accounts.repository;


import com.chandima.accounts.repository.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * only queries are really tested in the tests beause save is provided by the framework.
 * but saving also is tested indirectly.
 */
@DataJpaTest
public class TransactionRepositoryTest {
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
    public void getByAccount_whenThereAreTransactions_returnsListSuccessfully() {
        Account account = accountRepository.save(AccountFixture.createAccount("001", 10, AccountType.Current, Currency.AUD));
        Account account2 = accountRepository.save(AccountFixture.createAccount("002", 20, AccountType.Current, Currency.AUD));

        transactionRepository.save(AccountFixture.createTransaction(account, new BigDecimal(245.44), 1 , TransactionType.Credit));
        transactionRepository.save(AccountFixture.createTransaction(account, new BigDecimal(444.44), 2 , TransactionType.Debit));
        transactionRepository.save(AccountFixture.createTransaction(account, new BigDecimal(346.12), 3 , TransactionType.Credit));
        transactionRepository.save(AccountFixture.createTransaction(account2, new BigDecimal(458.12), 4 , TransactionType.Debit));

        List<Transaction> transactions = transactionRepository.findByAccount(account);
        assertEquals(3, transactions.size());
        List<Transaction> transactions2 = transactionRepository.findByAccount(account2);
        assertEquals(1, transactions2.size());
        assertEquals(new BigDecimal(458.12), transactions2.get(0).getAmount());

    }
}
