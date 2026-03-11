package com.leetcrawler.app.model.enums;

public enum AccountType {
    NORMAL,
    PREMIUM;

    public String display() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
