package com.irarrazabal.sublime.exceptions;

public class ProductNotFoundException  extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Producto con el id " + id + " no encontrado");
    }
}
