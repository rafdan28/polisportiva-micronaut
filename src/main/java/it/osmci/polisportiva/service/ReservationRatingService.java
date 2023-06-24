package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.ReservationRating;


public interface ReservationRatingService {
    ReservationRating createReservationRating(ReservationRating reservationRating);
    Object findAll();
    ReservationRating getReservationRatingById(Long reservationRatingId);
    Object deleteReservationRatingById(Long reservationRatingId);
}
