package com.example.common.enums;

import java.util.Arrays;

public enum PaymentType {
    CREDIT("credit"),
    DEBIT("debit"),
    CASH("cash");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValidPaymentType(String type) {
        return Arrays.stream(values())
                .anyMatch(pt -> pt.getValue().equals(type));
    }
}
