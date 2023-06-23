package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFieldService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/sports-fields")
public class SportsFieldController {

    @Inject
    private SportsFieldService sportsFieldService;

    @Post
    public HttpResponse<SportsField> createSportsField(@Body @Valid SportsField sportsField) {
        return HttpResponse.created(sportsFieldService.createSportsField(sportsField));
    }

    @Get
    public HttpResponse<List<SportsField>> findAll() {
        List<SportsField> sportsFieldList = sportsFieldService.findAll();
        if(sportsFieldList.size() != 0){
            return HttpResponse.ok(sportsFieldList);
        }
        return HttpResponse.notFound();
    }

    @Get("/{sportsFieldId}")
    public HttpResponse<SportsField> getSportsFieldsById(@PathVariable Long sportsFieldId) {
      return HttpResponse.ok(sportsFieldService.getSportsFieldById(sportsFieldId));
    }

    @Get("/filter_by_owner_sport")
    public HttpResponse<Object> getSportsFieldsByOwnerIdBySport(@QueryValue Long ownerId, @QueryValue String sport) {
        List<SportsField> sportsFieldList = sportsFieldService.getSportsFieldsByOwnerIdBySport(ownerId, sport);
        if(sportsFieldList.size() != 0){
            return HttpResponse.ok(sportsFieldList);
        }
        ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports field with this id/sport!");
        customException.setStackTrace(new StackTraceElement[0]);
        return HttpResponse.notFound(customException);
    }

    @Delete("/{sportsFieldId}")
    public void deleteSportsFieldsById(@PathVariable Long sportsFieldId) {
        sportsFieldService.deleteSportsFieldById(sportsFieldId);
    }
}
