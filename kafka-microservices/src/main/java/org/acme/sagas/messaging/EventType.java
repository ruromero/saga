package org.acme.sagas.messaging;

public class EventType {

    private String reservation;
    private String event;

    public String getReservation() {
        return reservation;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }
}
