package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.service.ReservationService;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Controller("/reservations")
public class ReservationController {
    @Inject
    private ReservationService reservationService;

    @Post
    public HttpResponse<Object> createReservation(@Body @Valid Reservation reservation){
        try {
            return HttpResponse.created(reservationService.createReservation(reservation));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Post("/{reservationId}/rating")
    public HttpResponse<Object> createReservationRatingByReservation(@PathVariable Long reservationId, @Body @Valid ReservationRating reservationRating){
        try {
            ReservationRating reservationRating1 = reservationService.createReservationRatingByReservation(reservationId, reservationRating);
            if (reservationRating1 == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no reservation with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException.getMessage());
            }
            return HttpResponse.ok(reservationRating1);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException.getMessage());
        }
    }

    @Get
    public HttpResponse<Object> findAll() {
        try {
            return HttpResponse.ok(reservationService.findAll());
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Get("/{reservationId}")
    public HttpResponse<Object> getReservationById(@PathVariable Long reservationId) {
        try {
            return HttpResponse.ok(reservationService.getReservationById(reservationId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }

    }

    @Put("/{reservationId}/status/{state}")
    public HttpResponse<Object> updateReservationStatusById(@PathVariable Long reservationId, @PathVariable ReservationStatus state){
        try {
            Reservation reservation = reservationService.updateReservationStatusById(reservationId, state);
            if (reservation == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no reservation with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException.getMessage());
            }
            return HttpResponse.ok(reservation);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException.getMessage());
        }
    }

    @Delete("/{reservationId}")
    public Object deleteReservationById(@PathVariable Long reservationId) {
        try{
            return HttpResponse.ok(reservationService.deleteReservationById(reservationId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

}
