package com.samuraiiway.sleuthjdbc.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "amount")
    private BigDecimal amount;

}
