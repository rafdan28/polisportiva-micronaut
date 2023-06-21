package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFacilityService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/sports-facility")
public class SportsFacilityController {
    @Inject
    private SportsFacilityService sportsFacilityServices;

    @Post
    public HttpResponse<SportsFacility> createSportFacility(@Body @Valid SportsFacility sportsFacility){
        return HttpResponse.created(sportsFacilityServices.createSportsFacility(sportsFacility));
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

    @Delete("/{sportsFacilityId}")
    public void deleteSportsFacilityById(@PathVariable Long sportsFacilityId) {
        sportsFacilityServices.deleteSportsFacilityById(sportsFacilityId);
    }
}
