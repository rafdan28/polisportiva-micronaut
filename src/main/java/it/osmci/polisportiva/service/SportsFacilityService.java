package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;

import java.util.List;

public interface SportsFacilityService {
    SportsFacility createSportsFacility(SportsFacility sportsFacility);
    SportsField createSportsFieldBySportsFacility(Long sportsFacilityId, SportsField sportsField);
    List<SportsFacility> findAll();
    SportsFacility getSportsFacilityById(Long sportsFacilityId);
    SportsFacility getSportsFacilityByOwnerId(Long ownerId);
    void deleteSportsFacilityById(Long sportsFacilityId);

}
