package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class ReservationServiceImplementation implements ReservationService {
    @Inject
    private ReservationRepository reservationRepository;

    @Inject
    private ReservationRatingService reservationRatingService;

    @Override
    public Reservation createReservation(Reservation reservation) {
        Objects.requireNonNull(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationRating createReservationRatingByReservation(Long reservationId, ReservationRating reservationRating) {
        try {
            Reservation reservation = getReservationById(reservationId);
            if(reservation != null){
                reservationRating.setReservation(reservation);
                return reservationRatingService.createReservationRating(reservationRating);
            }
            return null;
        }
        catch (ResourceNotFoundException e){
            return null;
        }
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ResourceNotFoundException("There is no reservation with this id!"));
    }

    @Override
    public void deleteReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
