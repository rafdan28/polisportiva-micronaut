package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    ReservationRating createReservationRatingByReservation(Long reservationId, ReservationRating reservationRating);
    List<Reservation> findAll();
    Reservation getReservationById(Long reservationId);
    void deleteReservationById(Long reservationId);
}
