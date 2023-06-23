package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;

import java.util.List;

public interface ReservationRatingService {
    ReservationRating createReservationRating(ReservationRating reservationRating);
    List<ReservationRating> findAll();
    ReservationRating getReservationRatingById(Long reservationRatingId);
    void deleteReservationRatingById(Long reservationRatingId);
}
