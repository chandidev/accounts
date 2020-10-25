package com.chandima.accounts.service;

import com.chandima.accounts.exception.UnauthorizedAccessException;
import com.chandima.accounts.repository.AccountRepository;
import com.chandima.accounts.repository.TransactionRepository;
import com.chandima.accounts.repository.model.Account;
import com.chandima.accounts.service.dto.AccountDto;
import com.chandima.accounts.service.dto.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public List<AccountDto> getAccounts(String customerId) {
        List<Account> accountList = accountRepository.getByCustomerId(customerId);
        return accountList.stream()
                .map(account -> {
                    return AccountDto.builder()
                            .accountName(account.getAccountName())
                            .accountType(account.getAccountType())
                            .balanceAmount(account.getBalanceAmount())
                            .balanceDate(account.getBalanceDate())
                            .currency(account.getCurrency())
                            .customerId(account.getCustomerId())
                            .accountNumber(account.getAccountNumber())
                            .build();
                }).collect(Collectors.toList());
    }


    @Transactional
    public List<TransactionDto> getTransactions(String customerId, String accountNumber) {
        Account account = accountRepository.getByCustomerIdAndAccountNumber(customerId, accountNumber);

        if (account != null) {
            return transactionRepository.findByAccount(account).stream()
                    .map(transaction -> TransactionDto.builder()
                            .accountName(account.getAccountName())
                            .accountNumber(account.getAccountNumber())
                            .currency(account.getCurrency())
                            .transactionType(transaction.getTransactionType())
                            .date(transaction.getDate())
                            .transactionId(transaction.getTransactionId())
                            .amount(transaction.getAmount())
                            .build()).collect(Collectors.toList());
        } else {
            //log to audit log and throw exception. audit logging is not done here.

            throw new UnauthorizedAccessException();
        }
    }
}
