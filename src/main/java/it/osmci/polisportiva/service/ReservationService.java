package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    ReservationRating createReservationRatingByReservation(Long reservationId, ReservationRating reservationRating);
    List<Reservation> findAll();
    Reservation getReservationById(Long reservationId);
    Reservation updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus);
    List<Reservation> getReservationBySportsFacilityId(Long sportsFacilityId, Date startDate, Date endDate);
    void deleteReservationById(Long reservationId);
}
