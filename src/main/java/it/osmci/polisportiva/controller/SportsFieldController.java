package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFieldService;
import jakarta.inject.Inject;

@Controller("/api/sports-fields")
public class SportsFieldController {

    @Inject
    private SportsFieldService sportsFieldService;

    @Get()
    public HttpResponse<SportsField> getSportsFields(@QueryValue Long filterByOwnerId, @QueryValue String filterBySport) {
        final String sport = (filterBySport == null) ? null : filterBySport.toString();
        return HttpResponse.ok(sportsFieldService.getSportsFields(filterByOwnerId, sport));
    }

  @Get("/{sportsFieldId}")
  public HttpResponse<SportsField> getSportsFieldsById(@PathVariable Long sportsFieldId) {
      return HttpResponse.ok(sportsFieldService.getSportsFieldById(sportsFieldId));
  }

}
