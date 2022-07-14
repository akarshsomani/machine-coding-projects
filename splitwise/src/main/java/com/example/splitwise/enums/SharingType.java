package com.example.splitwise.enums;

public enum SharingType {

    PERCENTAGE_SHARING ("PERCENTAGE_SHARING"),
    EXACT_AMOUNT ("EXACT_AMOUNT"),
    EQUAL_SHARING ("EQUAL_SHARING");

    private final String name;

    private SharingType(String s) {
        name = s;
    }

    }
