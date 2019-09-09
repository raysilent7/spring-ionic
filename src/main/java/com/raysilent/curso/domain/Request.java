package com.raysilent.curso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="request")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address shipmentAddress;

    @OneToMany(mappedBy="id.request")
    private Set<RequestItem> items = new HashSet<>();

    public Request() {
    }

    public Request(Integer id, Date instant, Client client, Address shipmentAddress) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.shipmentAddress = shipmentAddress;
    }

    public double getTotalValue() {
        double soma = 0.0;
        for (RequestItem ri : items) {
            soma = soma + ri.getSubTotal();
        }
        return soma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(Address shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public Set<RequestItem> getItems() {
        return items;
    }

    public void setItems(Set<RequestItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return getId().equals(request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Request number: ");
        builder.append(getId());
        builder.append(", Request date: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", Client: ");
        builder.append(getClient().getName());
        builder.append(", Payment status: ");
        builder.append(getPayment().getStatus().getDescricao());
        builder.append("\nDetails:\n");
        for (RequestItem ri : getItems()) {
            builder.append(ri.toString());
        }
        builder.append("Total value: ");
        builder.append(nf.format(getTotalValue()));
        return builder.toString();
    }
}
