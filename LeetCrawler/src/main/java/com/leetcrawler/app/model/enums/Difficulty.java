package com.leetcrawler.app.model.enums;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public String display() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
