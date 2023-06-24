package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.pojo.SportsReservation;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFacilityService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller("/sports-facilities")
public class SportsFacilityController {
    @Inject
    private SportsFacilityService sportsFacilityServices;

    @Post
    public HttpResponse<Object> createSportFacility(@Body @Valid SportsFacility sportsFacility){
        try {
            return HttpResponse.created(sportsFacilityServices.createSportsFacility(sportsFacility));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Post("/{sportsFacilityId}/sports-fields")
    public HttpResponse<Object> createSportsFieldBySportsFacility(@PathVariable Long sportsFacilityId, @Body @Valid SportsField sportsField){
        try {
            SportsField sportsField1 = sportsFacilityServices.createSportsFieldBySportsFacility(sportsFacilityId, sportsField);
            if (sportsField1 == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no sports facility with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException.getMessage());
            }
            return HttpResponse.ok(sportsField1);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException.getMessage());
        }
    }

    @Get
    public HttpResponse<Object> findSportsFacility(@QueryValue Optional<Long> filter_by_owner_id) {
        if (filter_by_owner_id.isEmpty()) {
            try {
                return HttpResponse.ok(sportsFacilityServices.findAll());
            }
            catch (Exception e){
                return HttpResponse.notFound(e.getMessage());
            }
        }
        else {
            try {
                List<SportsFacility> sportsFacilityList = sportsFacilityServices.getSportsFacilityByOwnerId(filter_by_owner_id.get());
                if (sportsFacilityList.size() != 0) {
                    return HttpResponse.ok(sportsFacilityList);
                } else {
                    ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports facility with this id!");
                    customException.setStackTrace(new StackTraceElement[0]);
                    return HttpResponse.notFound(customException.getMessage());
                }
            }
            catch (Exception e){
                Exception customException = new Exception(e.getMessage());
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException.getMessage());
            }
        }
    }

    @Get("/{sportsFacilityId}")
    public HttpResponse<Object> getSportsFacilityById(@PathVariable Long sportsFacilityId) {
        try {
            return HttpResponse.ok(sportsFacilityServices.getSportsFacilityById(sportsFacilityId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Get("/{sportsFacilityId}/reservations-summaries")
    public HttpResponse<Object> getReservationSummaryBySportsFacilityId(@PathVariable Long sportsFacilityId, @QueryValue String start_date, @QueryValue String end_date){
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date startDate = inputFormat.parse(start_date);
            Date endDate = inputFormat.parse(end_date);

            SportsReservation sportsReservation = sportsFacilityServices.getReservationSummaryBySportsFacilityId(sportsFacilityId, startDate, endDate);
            if (sportsReservation == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no sports facility with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException.getMessage());
            }
            sportsReservation.setStartDateTime(start_date);
            sportsReservation.setEndDateTime(end_date);
            return HttpResponse.ok(sportsReservation);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException.getMessage());
        }
    }

    @Delete("/{sportsFacilityId}")
    public Object deleteSportsFacilityById(@PathVariable Long sportsFacilityId) {
        try{
            return HttpResponse.ok(sportsFacilityServices.deleteSportsFacilityById(sportsFacilityId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

//    @Get
//    public HttpResponse<List<SportsFacility>> findAll() {
//        List<SportsFacility> sportsFacilityList = sportsFacilityServices.findAll();
//        if(sportsFacilityList.size() != 0){
//            return HttpResponse.ok(sportsFacilityList);
//        }
//        return HttpResponse.notFound();
//    }

//    @Get("/filter_by_owner/{ownerId}")
//    public HttpResponse<Object> getSportsFacilityByOwnerId(@PathVariable Long ownerId) {
//        try {
//            List<SportsFacility> sportsFacilityList = sportsFacilityServices.getSportsFacilityByOwnerId(ownerId);
//            if (sportsFacilityList.size() != 0) {
//                return HttpResponse.ok(sportsFacilityList);
//            } else {
//                ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports facility with this id!");
//                customException.setStackTrace(new StackTraceElement[0]);
//                return HttpResponse.notFound(customException);
//            }
//        }
//        catch (Exception e){
//            Exception customException = new Exception(e.getMessage());
//            customException.setStackTrace(new StackTraceElement[0]);
//            return HttpResponse.notFound(new ResourceNotFoundException("There are no user-associated sports facility with this id!"));
//        }
//    }
}
