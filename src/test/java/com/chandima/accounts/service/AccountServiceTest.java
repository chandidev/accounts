package com.chandima.accounts.service;


import com.chandima.accounts.exception.UnauthorizedAccessException;
import com.chandima.accounts.repository.AccountFixture;
import com.chandima.accounts.repository.AccountRepository;
import com.chandima.accounts.repository.TransactionRepository;
import com.chandima.accounts.repository.model.*;
import com.chandima.accounts.service.dto.AccountDto;
import com.chandima.accounts.service.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void getAccounts_whenPresentInDatabase_returnsResults() {
        Account account = AccountFixture.createAccount("001",2, AccountType.Saving, Currency.AUD );
        List<Account> accountList = Collections.singletonList(account);
        when(accountRepository.getByCustomerId("001")).thenReturn(accountList);
        List<AccountDto> accountDtos = accountService.getAccounts("001");
        verify(accountRepository, times(1)).getByCustomerId("001");
        assertEquals(1, accountDtos.size());
    }

    @Test
    public void getAccounts_whenNotPresentInDatabase_returnsEmptyList() {
        List<AccountDto> list = accountService.getAccounts("001");
        verify(accountRepository, times(1)).getByCustomerId("001");
        assertEquals(0, list.size());
    }

    @Test
    public void getAccounts_whenPresentInDatabase_createDtoCorrectly() {
        Account account = AccountFixture.createAccount("001",2, AccountType.Saving, Currency.AUD );
        List<Account> accountList = Collections.singletonList(account);
        when(accountRepository.getByCustomerId("001")).thenReturn(accountList);
        List<AccountDto> accountDtos = accountService.getAccounts("001");
        verify(accountRepository, times(1)).getByCustomerId("001");

        AccountDto accountDto = accountDtos.get(0);
        assertEquals(account.getAccountName(), accountDto.getAccountName());
        assertEquals(account.getCustomerId(), accountDto.getCustomerId());
        assertEquals(account.getAccountType(), accountDto.getAccountType());
        assertEquals(account.getBalanceAmount(), accountDto.getBalanceAmount());
        assertEquals(account.getBalanceDate(), accountDto.getBalanceDate());
        assertEquals(account.getCurrency(), accountDto.getCurrency());
    }

    @Test
    public void getTransactions_WhenAccountAndCustomeIdDoNotMatch_throwsException() {

        when(accountRepository.getByCustomerIdAndAccountNumber("001", "TR001"))
                .thenReturn(null);
        assertThrows(UnauthorizedAccessException.class,
                () ->  accountService.getTransactions("001", "TR001"));
    }

    @Test
    public void getTransactions_WhenAccountAndCustomeIdDoMatch_ReturnsResults() {

        Account account = AccountFixture.createAccount("001", 1, AccountType.Current,
                Currency.AUD);
        when(accountRepository.getByCustomerIdAndAccountNumber("001", account.getAccountNumber()))
                .thenReturn(account);
        Transaction transaction = AccountFixture.createTransaction(account, new BigDecimal("10.22"),
                3, TransactionType.Debit);
        when(transactionRepository.findByAccount(account)).thenReturn(Arrays.asList(transaction, transaction));
        List<TransactionDto> transactionDtos = accountService.getTransactions("001", account.getAccountNumber());

        assertEquals(2, transactionDtos.size());

    }

    @Test
    public void getTransactions_WhenAccountAndCustomeIdDoMatch_transformsProperly() {

        Account account = AccountFixture.createAccount("001", 1, AccountType.Current,
                Currency.AUD);
        when(accountRepository.getByCustomerIdAndAccountNumber("001", account.getAccountNumber()))
                .thenReturn(account);
        Transaction transaction = AccountFixture.createTransaction(account, new BigDecimal("10.22"),
                3, TransactionType.Debit);
        when(transactionRepository.findByAccount(account)).thenReturn(Arrays.asList(transaction, transaction));
        List<TransactionDto> transactionDtos = accountService.getTransactions("001", account.getAccountNumber());

        TransactionDto transactionDto = transactionDtos.get(0);
        //verify each fields here. not implementing all for this excercize.
        assertEquals(account.getAccountName(), transactionDto.getAccountName());

    }
}
