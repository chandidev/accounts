package com.chandima.accounts.repository.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Integer transactionId;
    @ManyToOne()
    private Account account;
    private Date date;
    private BigDecimal amount;
    private TransactionType transactionType;
    @Version
    private Integer version;
}
