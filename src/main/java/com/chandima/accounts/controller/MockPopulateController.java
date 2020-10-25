package com.chandima.accounts.controller;

import com.chandima.accounts.repository.AccountRepository;
import com.chandima.accounts.repository.TransactionRepository;
import com.chandima.accounts.repository.model.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/test")
@AllArgsConstructor
public class MockPopulateController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @GetMapping("/populate")
    public String populate() {
        Account account = accountRepository.save(Account.builder()
                .accountName("Current")
                .accountType(AccountType.Current)
                .balanceAmount(new BigDecimal("23.33"))
                .balanceDate(new Date())
                .currency(Currency.AUD)
                .customerId("001")
                .accountNumber("XX003")
                .build());
        accountRepository.save(Account.builder()
                .accountName("Saving")
                .accountType(AccountType.Saving)
                .balanceAmount(new BigDecimal("23.33"))
                .balanceDate(new Date())
                .currency(Currency.EUR)
                .customerId("001")
                .accountNumber("DD004")
                .build());
        accountRepository.save(Account.builder()
                .accountName("Other person's account")
                .accountType(AccountType.Saving)
                .balanceAmount(new BigDecimal("23.33"))
                .balanceDate(new Date())
                .currency(Currency.EUR)
                .customerId("003")
                .accountNumber("DD005")
                .build());

        transactionRepository.save(Transaction.builder()
                .account(account)
                .amount(new BigDecimal("33.44"))
                .transactionType(TransactionType.Credit)
                .date(new Date())
                .build());

        transactionRepository.save(Transaction.builder()
                .account(account)
                .amount(new BigDecimal("55.99"))
                .transactionType(TransactionType.Debit)
                .date(new Date())
                .build());
        return "OK";
    }


}
