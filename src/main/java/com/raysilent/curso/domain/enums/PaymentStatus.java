package com.raysilent.curso.domain.enums;

public enum PaymentStatus {

    PENDETE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    PaymentStatus(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PaymentStatus toEnum(Integer id) {

        if (id == null) {
            return null;
        }
        for (PaymentStatus x : PaymentStatus.values()) {
            if (id.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido " + id);
    }
}
