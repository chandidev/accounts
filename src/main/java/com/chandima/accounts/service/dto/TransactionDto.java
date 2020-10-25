package com.chandima.accounts.service.dto;

import com.chandima.accounts.repository.model.Currency;
import com.chandima.accounts.repository.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class TransactionDto {

    private Integer transactionId;
    private Date date;
    private BigDecimal amount;
    private String accountName;
    private String accountNumber;
    private Currency currency;
    private TransactionType transactionType;
}
