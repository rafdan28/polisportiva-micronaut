package it.osmci.polisportiva.altro.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReservationStatus {
    REJECTED, PENDING, ACCEPTED;

    @JsonCreator
    public static ReservationStatus fromString(String value) {
        return ReservationStatus.valueOf(value.toUpperCase());
    }
}
