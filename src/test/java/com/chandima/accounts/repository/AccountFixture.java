package com.chandima.accounts.repository;

import com.chandima.accounts.repository.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AccountFixture {

    public static Account createAccount(String customerId , int accountIndex, AccountType accountType, Currency currency) {
        return Account.builder()
                .accountName("Account" + accountIndex)
                .accountType(accountType)
                .balanceDate(getOffSetDate(accountIndex))
                .currency(currency)
                .customerId(customerId)
                .accountNumber(currency.name() + accountType.name() + accountIndex)
                .build();
    }

    private static Date getOffSetDate(int accountIndex) {
        return Date.from(LocalDate.of(2020, 9, accountIndex % 30)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Transaction createTransaction(Account account, BigDecimal amount, int index, TransactionType transactionType) {
        return Transaction.builder()
                .account(account)
                .amount(amount)
                .date(getOffSetDate( index))
                .transactionType(transactionType)
                .build();
    }
}
