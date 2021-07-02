package com.samuraiiway.sleuthjdbc.constant;

public enum SqlOperation {

    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String name;

    SqlOperation(String name) {
        this.name = name;
    }
}
