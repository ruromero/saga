package org.acme.sagas.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Item {

    String reference;
    String name;
    Float price;
    Integer quantity;

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getReference() {
        return reference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("[ref:").append(reference)
            .append(",name:").append(name)
            .append(",price:").append(price)
            .append(",quantity:").append(quantity).append("]")
            .toString();
    }
}
