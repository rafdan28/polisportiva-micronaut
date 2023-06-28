package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Date;
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
        List<Reservation> reservationList = reservationRepository.findAll();
        if(reservationList.size() != 0) return reservationList;
        else throw new ResourceNotFoundException("There are no reservation present.");
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ResourceNotFoundException("There is no reservation with this id!"));
    }

    @Override
    public Reservation updateReservationStatusById(Long reservationId, ReservationStatus reservationStatus) {
        try {
            Reservation reservation = getReservationById(reservationId);
            if(reservation != null){
                reservation.setState(reservationStatus);
                return reservationRepository.update(reservation);
            }
            return null;
        }
        catch (ResourceNotFoundException e){
            return null;
        }
    }

    @Override
    public List<Reservation> getReservationBySportsFacilityId(Long sportsFacilityId, Date startDate, Date endDate) {
        try {
            return reservationRepository.getReservationBySportsFacilityId(sportsFacilityId, startDate, endDate);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Reservation> getReservationBySportsFacilityId(Long sportsFacilityId) {
        try {
            return reservationRepository.getReservationBySportsFacilityId(sportsFacilityId);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Object deleteReservationById(Long reservationId) {
        Objects.requireNonNull(reservationId);
        if(reservationRepository.existsById(reservationId)){
            reservationRepository.deleteById(reservationId);
            return "This reservation id has been deleted";
        }
        else throw new ResourceNotFoundException("This id doesn't identify any reservation!");
    }
}
