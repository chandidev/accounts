package com.chandima.accounts.service.dto;

import com.chandima.accounts.repository.model.AccountType;
import com.chandima.accounts.repository.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountDto {
    private String customerId;
    private String accountName;
    private AccountType accountType;
    private Date balanceDate;
    private Currency currency;
    private BigDecimal balanceAmount;
    private String accountNumber;
}
