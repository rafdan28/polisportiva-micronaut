package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.dto.ReservationDTO;
import it.osmci.polisportiva.altro.dto.ReservationStatusDTO;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.ReservationRating;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.service.ReservationService;
import it.osmci.polisportiva.service.SportsFieldService;
import it.osmci.polisportiva.service.UserService;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Controller("/reservations")
public class ReservationController {
    @Inject
    private ReservationService reservationService;

    @Inject
    private SportsFieldService sportsFieldService;

    @Inject
    private UserService userService;

    @Post
    public HttpResponse<Object> createReservation(@Body @Valid ReservationDTO reservationDTO){
        try {
            SportsField sportsField = sportsFieldService.getSportsFieldById(reservationDTO.getSportsFieldId());
            User user = userService.getUserById(reservationDTO.getOwnerId());
            Reservation reservation = new Reservation();
            reservation.setSportsField(sportsField);
            reservation.setUser(user);
            reservation.setStartDateTime(reservationDTO.getDateRange().getStartDate());
            reservation.setEndDateTime(reservationDTO.getDateRange().getEndDate());
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

    @Put("/{reservationId}/status")
    public HttpResponse<Object> updateReservationStatusById(@PathVariable Long reservationId, @Body ReservationStatusDTO statusDTO){
        try {
            Reservation reservation = reservationService.updateReservationStatusById(reservationId, statusDTO.getState());
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
