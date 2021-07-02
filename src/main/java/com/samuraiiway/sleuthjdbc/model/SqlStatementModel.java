package com.samuraiiway.sleuthjdbc.model;

import com.samuraiiway.sleuthjdbc.constant.SqlOperation;
import lombok.Data;

import java.util.List;

@Data
public class SqlStatementModel {

    private SqlOperation operation;
    private List<String> tables;
}
