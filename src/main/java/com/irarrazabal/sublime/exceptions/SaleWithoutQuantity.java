package com.irarrazabal.sublime.exceptions;

public class SaleWithoutQuantity extends RuntimeException {

    public SaleWithoutQuantity(String message) {
        super(message);
    }
}
