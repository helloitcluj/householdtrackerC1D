package com.helloit.householdtracker.ux.spring.account;

/**
 */
public class AccountResult {

    public enum Kind {
        SUCCESS,
        ERROR
    }

    private Kind kind;
    private String message;

    public AccountResult(final Kind kind) {
        this.kind = kind;
        this.message = null;
    }

    public AccountResult(final Kind kind, final String message) {
        this.kind = kind;
        this.message = message;
    }

}
