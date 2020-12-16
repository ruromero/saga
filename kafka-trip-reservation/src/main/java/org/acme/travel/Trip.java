package org.acme.travel;

public class Trip {

    String origin;
    String destination;
    String hotel;
    String customerId;

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

}