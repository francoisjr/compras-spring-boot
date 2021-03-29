package com.compras.spring.boot.entities.enums;

public enum CategoriaProduto {

    PRIMARIO(1),
    SECUNDARIO(2);

    private int code;

    CategoriaProduto(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CategoriaProduto valueOf(int code) {
        for (CategoriaProduto value : CategoriaProduto.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de categoria de produto invalido");
    }
}
