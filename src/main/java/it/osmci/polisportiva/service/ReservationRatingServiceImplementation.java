package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.repository.ReservationRatingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class ReservationRatingServiceImplementation implements ReservationRatingService{
    @Inject
    private ReservationRatingRepository reservationRatingRepository;

    @Override
    public ReservationRating createReservationRating(ReservationRating reservationRating) {
        Objects.requireNonNull(reservationRating);
        return reservationRatingRepository.save(reservationRating);
    }

    @Override
    public List<ReservationRating> findAll() {
        return reservationRatingRepository.findAll();
    }

    @Override
    public ReservationRating getReservationRatingById(Long reservationRatingId) {
        return reservationRatingRepository.findById(reservationRatingId).orElseThrow(() -> new ResourceNotFoundException("There is no reservation rating with this id!"));
    }

    @Override
    public void deleteReservationRatingById(Long reservationRatingId) {
        reservationRatingRepository.deleteById(reservationRatingId);
    }
}
