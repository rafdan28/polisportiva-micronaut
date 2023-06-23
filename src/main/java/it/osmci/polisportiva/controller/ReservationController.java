package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.ReservationService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/reservations")
public class ReservationController {
    @Inject
    private ReservationService reservationService;

    @Post
    public HttpResponse<Reservation> createReservation(@Body @Valid Reservation reservation){
        return HttpResponse.created(reservationService.createReservation(reservation));
    }

    @Post("/{reservationId}/rating")
    public HttpResponse<Object> createReservationRatingByReservation(@PathVariable Long reservationId, @Body @Valid ReservationRating reservationRating){
        try {
            ReservationRating reservationRating1 = reservationService.createReservationRatingByReservation(reservationId, reservationRating);
            if (reservationRating1 == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no reservation with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException);
            }
            return HttpResponse.ok(reservationRating1);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException);
        }
    }

    @Get
    public HttpResponse<List<Reservation>> findAll() {
        List<Reservation> reservationList = reservationService.findAll();
        if(reservationList.size() != 0){
            return HttpResponse.ok(reservationList);
        }
        return HttpResponse.notFound();
    }

    @Get("/{reservationId}")
    public HttpResponse<Reservation> getReservationById(@PathVariable Long reservationId) {
        return HttpResponse.ok(reservationService.getReservationById(reservationId));
    }

    @Delete("/{reservationId}")
    public void deleteReservationById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
    }

}
