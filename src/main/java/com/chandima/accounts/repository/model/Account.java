package com.chandima.accounts.repository.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String accountNumber;
    private String customerId;
    private String accountName;
    private AccountType accountType;
    private Date balanceDate;
    private Currency currency;
    private BigDecimal balanceAmount;
    @Version()
    private Integer version;

}
