package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.service.ReservationService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/reservation")
public class ReservationController {
    @Inject
    private ReservationService reservationService;

    @Post
    public HttpResponse<Reservation> createSportFacility(@Body @Valid Reservation reservation){
        return HttpResponse.created(reservationService.createReservation(reservation));
    }

    @Get
    public HttpResponse<List<Reservation>> findAll() {
        List<Reservation> sportsFacilityList = reservationService.findAll();
        if(sportsFacilityList.size() != 0){
            return HttpResponse.ok(sportsFacilityList);
        }
        return HttpResponse.notFound();
    }

    @Get("/{reservationId}")
    public HttpResponse<Reservation> getSportsFacilityById(@PathVariable Long reservationId) {
        return HttpResponse.ok(reservationService.getReservationById(reservationId));
    }

    @Delete("/{reservationId}")
    public void deleteSportsFacilityById(@PathVariable Long reservationId) {
        reservationService.deleteReservationById(reservationId);
    }

}
