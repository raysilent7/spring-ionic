package com.raysilent.curso.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.raysilent.curso.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment implements Serializable {

    private Integer stallments;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentStatus status, Request request, Integer stallments) {
        super(id, status, request);
        this.stallments = stallments;
    }

    public Integer getStallments() {
        return stallments;
    }

    public void setStallments(Integer stallments) {
        this.stallments = stallments;
    }
}
