package com.markteplace.bazan.markteplace_web.infrastructure.exceptions;

public class SaldoInsuficiente extends RuntimeException {
    public SaldoInsuficiente(String message) {
        super(message);
    }
}
