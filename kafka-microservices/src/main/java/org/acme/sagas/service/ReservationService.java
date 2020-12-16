package org.acme.sagas.service;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

import org.acme.sagas.messaging.Reservation;

@ApplicationScoped
public class ReservationService {

    private static final String REJECTED_STATUS = "Rejected";
    private static final String CONFIRMED_STATUS = "Confirmed";
    private static final String CANCELLED_STATUS = "Cancelled";

    private Map<String, Map<String, String>> reservations = new HashMap<>();

    public String doReservation(String reservationType, Reservation reservation) {
        String reservationStatus = calculateReservationResult(reservation);
        return setReservationStatus(reservationType, reservation.getClientId(), reservationStatus);
    }

    public String cancelReservation(String reservationType, Reservation reservation) {
        return setReservationStatus(reservationType, reservation.getClientId(), CANCELLED_STATUS);
    }

    private String setReservationStatus(String type, String clientId, String status) {
        Map<String, String> typeReservations = reservations.computeIfAbsent(type, k -> new HashMap<>());
        String reservationStatus = typeReservations.get(clientId);
        if (!REJECTED_STATUS.equals(reservationStatus) && !CANCELLED_STATUS.equals(reservationStatus)) {
            reservationStatus = status;
        }
        typeReservations.put(clientId, reservationStatus);
        return reservationStatus;
    }

    public Map<String, String> getReservations(String type) {
        return reservations.get(type);
    }

    private String calculateReservationResult(Reservation reservation) {
        if (reservation == null || reservation.getStatus() != null) {
            return null;
        }
        if (reservation.getName().startsWith("X")) {
            return REJECTED_STATUS;
        }
        return CONFIRMED_STATUS;
    }
}
