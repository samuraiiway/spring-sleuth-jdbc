package com.samuraiiway.sleuthjdbc.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.samuraiiway.sleuthjdbc.entity.TransactionEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_category")
    private String productCategory;

    @JsonProperty("amount")
    private BigDecimal amount;

    public static TransactionModel from(TransactionEntity entity) {
        TransactionModel response = new TransactionModel();
        response.setId(entity.getId());
        response.setUserId(entity.getUserId());
        response.setProductId(entity.getProductId());
        response.setProductCategory(entity.getProductCategory());
        response.setAmount(entity.getAmount());

        return response;
    }

    public TransactionEntity toEntity() {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(this.getId());
        entity.setUserId(this.getUserId());
        entity.setProductId(this.getProductId());
        entity.setProductCategory(this.getProductCategory());
        entity.setAmount(this.getAmount());

        return entity;
    }

    public TransactionEntity toEntity(Long id) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(id);
        entity.setUserId(this.getUserId());
        entity.setProductId(this.getProductId());
        entity.setProductCategory(this.getProductCategory());
        entity.setAmount(this.getAmount());

        return entity;
    }
}
