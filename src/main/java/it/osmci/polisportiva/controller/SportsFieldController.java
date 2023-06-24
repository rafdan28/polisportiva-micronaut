package it.osmci.polisportiva.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.service.SportsFieldService;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller("/sports-fields")
public class SportsFieldController {

    @Inject
    private SportsFieldService sportsFieldService;

    @Post
    public HttpResponse<SportsField> createSportsField(@Body @Valid SportsField sportsField) {
        return HttpResponse.created(sportsFieldService.createSportsField(sportsField));
    }

    @Get
    public HttpResponse<Object> findSportsField(@QueryValue Optional<Long> filter_by_owner_id, @QueryValue Optional<String> filter_by_sport) {
        if (filter_by_owner_id.isEmpty() && filter_by_sport.isEmpty()) {
            List<SportsField> sportsFieldList = sportsFieldService.findAll();
            if(sportsFieldList.size() != 0){
                return HttpResponse.ok(sportsFieldList);
            }
            return HttpResponse.notFound();
        }
        else if(filter_by_owner_id.isPresent() && filter_by_sport.isPresent()){
            List<SportsField> sportsFieldList = sportsFieldService.getSportsFieldsByOwnerIdBySport(filter_by_owner_id.get(), filter_by_sport.get());
            if(sportsFieldList.size() != 0){
                return HttpResponse.ok(sportsFieldList);
            }
            ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports field with this id and sport!");
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException);
        }
        else if(filter_by_owner_id.isPresent()) {
            List<SportsField> sportsFieldList = sportsFieldService.getSportsFieldsByOwnerId(filter_by_owner_id.get());
            if(sportsFieldList.size() != 0){
                return HttpResponse.ok(sportsFieldList);
            }
            ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports field with this id!");
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException);
        }
        else{
            List<SportsField> sportsFieldList = sportsFieldService.getSportsFieldsBySport(filter_by_sport.get());
            if(sportsFieldList.size() != 0){
                return HttpResponse.ok(sportsFieldList);
            }
            ResourceNotFoundException customException = new ResourceNotFoundException("There are no sports field with this sport!");
            customException.setStackTrace(new StackTraceElement[0]);
            return HttpResponse.notFound(customException);
        }
    }

    @Get("/{sportsFieldId}")
    public HttpResponse<SportsField> getSportsFieldsById(@PathVariable Long sportsFieldId) {
      return HttpResponse.ok(sportsFieldService.getSportsFieldById(sportsFieldId));
    }

    @Delete("/{sportsFieldId}")
    public void deleteSportsFieldsById(@PathVariable Long sportsFieldId) {
        sportsFieldService.deleteSportsFieldById(sportsFieldId);
    }

    //    @Get
//    public HttpResponse<List<SportsField>> findAll() {
//        List<SportsField> sportsFieldList = sportsFieldService.findAll();
//        if(sportsFieldList.size() != 0){
//            return HttpResponse.ok(sportsFieldList);
//        }
//        return HttpResponse.notFound();
//    }

//    @Get("/filter_by_owner_sport")
//    public HttpResponse<Object> getSportsFieldsByOwnerIdBySport(@QueryValue Long ownerId, @QueryValue String sport) {
//        List<SportsField> sportsFieldList = sportsFieldService.getSportsFieldsByOwnerIdBySport(ownerId, sport);
//        if(sportsFieldList.size() != 0){
//            return HttpResponse.ok(sportsFieldList);
//        }
//        ResourceNotFoundException customException = new ResourceNotFoundException("There are no user-associated sports field with this id/sport!");
//        customException.setStackTrace(new StackTraceElement[0]);
//        return HttpResponse.notFound(customException);
//    }
}
