package com.raysilent.curso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.raysilent.curso.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@JsonTypeName("billetPayment")
public class BilletPayment extends Payment implements Serializable {

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date expirationDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date paymentDate;

    public BilletPayment() {
    }

    public BilletPayment(Integer id, PaymentStatus status, Request request, Date expirationDate, Date paymentDate) {
        super(id, status, request);
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
