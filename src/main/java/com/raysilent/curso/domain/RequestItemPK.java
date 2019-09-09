package com.raysilent.curso.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RequestItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestItemPK)) return false;
        RequestItemPK that = (RequestItemPK) o;
        return getRequest().equals(that.getRequest()) &&
                getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequest(), getProduct());
    }
}
