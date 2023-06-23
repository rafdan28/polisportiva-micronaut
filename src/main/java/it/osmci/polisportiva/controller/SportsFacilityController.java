package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFacilityService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/sports-facilities")
public class SportsFacilityController {
    @Inject
    private SportsFacilityService sportsFacilityServices;

    @Post
    public HttpResponse<SportsFacility> createSportFacility(@Body @Valid SportsFacility sportsFacility){
        return HttpResponse.created(sportsFacilityServices.createSportsFacility(sportsFacility));
    }

    @Post("/{sportsFacilityId}/sports-fields")
    public HttpResponse<Object> createSportsFieldBySportsFacility(@PathVariable Long sportsFacilityId, @Body @Valid SportsField sportsField){
        try {
            SportsField sportsField1 = sportsFacilityServices.createSportsFieldBySportsFacility(sportsFacilityId, sportsField);
            if (sportsField1 == null) {
                ResourceNotFoundException customException = new ResourceNotFoundException("There is no sports facility with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException);
            }
            return HttpResponse.ok(sportsField1);
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException);
        }
    }

    @Get
    public HttpResponse<List<SportsFacility>> findAll() {
        List<SportsFacility> sportsFacilityList = sportsFacilityServices.findAll();
        if(sportsFacilityList.size() != 0){
            return HttpResponse.ok(sportsFacilityList);
        }
        return HttpResponse.notFound();
    }

    @Get("/{sportsFacilityId}")
    public HttpResponse<SportsFacility> getSportsFacilityById(@PathVariable Long sportsFacilityId) {
        return HttpResponse.ok(sportsFacilityServices.getSportsFacilityById(sportsFacilityId));
    }

    @Get("/filter_by_owner/{ownerId}")
    public HttpResponse<Object> getSportsFacilityByOwnerId(@PathVariable Long ownerId) {
        try {
            List<SportsFacility> sportsFacilityList = sportsFacilityServices.getSportsFacilityByOwnerId(ownerId);
            if (sportsFacilityList.size() != 0) {
                return HttpResponse.ok(sportsFacilityList);
            } else {
                ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports facility with this id!");
                customException.setStackTrace(new StackTraceElement[0]);
                return HttpResponse.notFound(customException);
            }
        }
        catch (Exception e){
            Exception customException = new Exception(e.getMessage());
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(new ResourceNotFoundException("There are no user-associated sports facility with this id!"));
        }

    }

    @Delete("/{sportsFacilityId}")
    public void deleteSportsFacilityById(@PathVariable Long sportsFacilityId) {
        sportsFacilityServices.deleteSportsFacilityById(sportsFacilityId);
    }
}
