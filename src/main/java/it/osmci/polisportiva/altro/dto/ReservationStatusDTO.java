package it.osmci.polisportiva.altro.dto;

import it.osmci.polisportiva.altro.enumeration.ReservationStatus;

public class ReservationStatusDTO {
    private ReservationStatus state;

    public ReservationStatus getState() {
        return state;
    }

    public void setState(ReservationStatus state) {
        this.state = state;
    }
}