package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFieldService;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/sports-fields")
public class SportsFieldController {

    @Inject
    private SportsFieldService sportsFieldService;

    @Get()
    public HttpResponse<List<SportsField>> getSportsFields(@QueryValue Long filter_by_owner_id, @QueryValue String filter_by_sport) {
        final String sport = (filter_by_sport == null) ? null : filter_by_sport.toString();
        return HttpResponse.ok(sportsFieldService.getSportsFields(filter_by_owner_id, sport));
    }

  @Get("/{sportsFieldId}")
  public HttpResponse<SportsField> getSportsFieldsById(@PathVariable Long sportsFieldId) {
      return HttpResponse.ok(sportsFieldService.getSportsFieldById(sportsFieldId));
  }

}
