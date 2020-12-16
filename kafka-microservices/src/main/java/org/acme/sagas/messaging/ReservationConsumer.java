package org.acme.sagas.messaging;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.jackson.JsonFormat;
import org.acme.sagas.service.ReservationService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class ReservationConsumer {

    private static final String SERVICE_NAME = "ReservationsService";
    private static final String REQUEST_TYPE = "Request";
    private static final String CANCEL_TYPE = "Cancel";

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ReservationService service;

    @PostConstruct
    public void initObjectMapper() {
        objectMapper.registerModule(JsonFormat.getCloudEventJacksonModule());
    }

    @Incoming("reservations")
    @Outgoing("trips")
    public String onReceive(String request) throws IOException {
        CloudEvent ce = objectMapper.readValue(request, CloudEvent.class);
        if (request == null || ce.getType() == null) {
            return null;
        }
        EventType type = getRequestType(ce.getType());
        if (type == null) {
            return null;
        }
        Reservation reservation = objectMapper.readValue(ce.getData(), Reservation.class);
        if (reservation != null) {
            if (CANCEL_TYPE.equals(type.getEvent())) {
                reservation.setStatus(service.cancelReservation(type.getReservation(), reservation));
            } else {
                reservation.setStatus(service.doReservation(type.getReservation(), reservation));
            }
            return objectMapper.writeValueAsString(CloudEventBuilder.v1()
                    .withId(UUID.randomUUID().toString())
                    .withType(type.getReservation() + reservation.getStatus())
                    .withSource(URI.create(SERVICE_NAME))
                    .withExtension("kogitoReferenceId", (String) ce.getExtension("kogitoProcessinstanceId"))
                    .withExtension("kogitoProcessId", (String) ce.getExtension("kogitoProcessId"))
                    .withData(objectMapper.writeValueAsBytes(reservation))
                    .build());
        }
        return null;
    }

    private EventType getRequestType(String ceType) {
        EventType type = new EventType();
        if (ceType.startsWith(REQUEST_TYPE)) {
            type.setEvent(REQUEST_TYPE);
            type.setReservation(ceType.substring(REQUEST_TYPE.length()));
        }
        if (ceType.startsWith(CANCEL_TYPE)) {
            type.setEvent(CANCEL_TYPE);
            type.setReservation(ceType.substring(CANCEL_TYPE.length()));
        }
        if (type.getEvent() != null) {
            return type;
        }
        return null;
    }


}