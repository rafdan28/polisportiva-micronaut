package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> findAll();
    Reservation getReservationById(Long reservationId);
    void deleteReservationById(Long reservationId);
}
