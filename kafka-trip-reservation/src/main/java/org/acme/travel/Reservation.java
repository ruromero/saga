package org.acme.travel;

public class Reservation {

    private String name;
    private String clientId;
    private String status;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}