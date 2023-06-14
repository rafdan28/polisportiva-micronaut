package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFieldService;
import jakarta.inject.Inject;

@Controller("/api/sportsfields")
public class SportsFieldController {

    @Inject
    private SportsFieldService sportsFieldService;

//    @Get("/list")
//    public HttpResponse<SportsFieldsPage> getSportsFields(
//            @QueryValue Integer pageNo,
//            @QueryValue Integer pageSize,
//            @QueryValue SportEnum filterBySport,
//            @QueryValue String sortBy,
//            @QueryValue Long filterByOwnerId
//    ) {
//
//    }

  @Get("/{sportsFieldId}")
  public HttpResponse<SportsField> getSportsFieldsById(@PathVariable Long sportsFieldId) {
      return HttpResponse.ok(sportsFieldService.getSportsFieldById(sportsFieldId));
  }

}
