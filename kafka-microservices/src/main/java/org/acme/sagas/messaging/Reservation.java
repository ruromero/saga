package org.acme.sagas.messaging;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Reservation {

    private String name;
    private String clientId;
    private String status;

    public Reservation() {

    }

    public Reservation(String name, String clientId, String status) {
        this.name = name;
        this.clientId = clientId;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getClientId() {
        return clientId;
    }

    public Reservation setStatus(String status) {
        this.status = status;
        return this;
    }

    public Reservation setName(String name) {
        this.name = name;
        return this;
    }

    public Reservation setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }
}
