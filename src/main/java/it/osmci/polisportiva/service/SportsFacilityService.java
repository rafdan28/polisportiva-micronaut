package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.SportsFacility;

import java.util.List;

public interface SportsFacilityService {
    SportsFacility createSportsFacility(SportsFacility sportsFacility);
    List<SportsFacility> findAll();
    SportsFacility getSportsFacilityById(Long sportsFacilityId);
    void deleteSportsFacilityById(Long sportsFacilityId);
}
