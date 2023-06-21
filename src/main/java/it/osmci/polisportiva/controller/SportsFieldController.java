package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
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

    @Delete("/{sportsFieldId}")
    public void deleteSportsFieldsById(@PathVariable Long sportsFieldId) {
        sportsFieldService.deleteSportsFieldById(sportsFieldId);
    }

//    @Get()
//    public HttpResponse<List<SportsField>> getSportsFields(@QueryValue Long filter_by_owner_id, @QueryValue String filter_by_sport) {
//        final String sport = (filter_by_sport == null) ? null : filter_by_sport.toString();
//        return HttpResponse.ok(sportsFieldService.getSportsFields(filter_by_owner_id, sport));
//    }

}
