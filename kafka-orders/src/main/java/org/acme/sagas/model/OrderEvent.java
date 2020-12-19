package org.acme.sagas.model;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class OrderEvent {

    Collection<Item> items;
    String paymentToken;
    String address;
    String clientId;

    public String getAddress() {
        return address;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getItemsAsString() {
        if(items == null) {
            return null;
        }
        return items.stream().map(Item::toString).collect(Collectors.joining(","));
    }
}