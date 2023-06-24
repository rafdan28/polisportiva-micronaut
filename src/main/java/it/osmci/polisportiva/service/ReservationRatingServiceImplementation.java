package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.repository.ReservationRatingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class ReservationRatingServiceImplementation implements ReservationRatingService {

    @Inject
    private ReservationRatingRepository reservationRatingRepository;

    @Override
    public ReservationRating createReservationRating(ReservationRating reservationRating) {
        Objects.requireNonNull(reservationRating);
        return reservationRatingRepository.save(reservationRating);
    }

    @Override
    public Object findAll() {
        List<ReservationRating> reservationRatingList = reservationRatingRepository.findAll();
        if(reservationRatingList.size() != 0) return reservationRatingList;
        else throw new ResourceNotFoundException("There are no users present.");
    }

    @Override
    public ReservationRating getReservationRatingById(Long reservationRatingId) {
        return reservationRatingRepository.findById(reservationRatingId).orElseThrow(() -> new ResourceNotFoundException("There is no reservation rating with this id!"));
    }

    @Override
    public Object deleteReservationRatingById(Long reservationRatingId) {
        Objects.requireNonNull(reservationRatingId);
        if(reservationRatingRepository.existsById(reservationRatingId)){
            reservationRatingRepository.deleteById(reservationRatingId);
            return "This reservation rating id has been deleted";
        }
        else throw new ResourceNotFoundException("This id doesn't identify any reservation rating!");
    }
}
