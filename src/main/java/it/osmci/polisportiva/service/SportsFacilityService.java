package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.pojo.SportsReservation;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;

import java.util.Date;
import java.util.List;

public interface SportsFacilityService {
    SportsFacility createSportsFacility(SportsFacility sportsFacility);
    SportsField createSportsFieldBySportsFacility(Long sportsFacilityId, SportsField sportsField);
    List<SportsFacility> findAll();
    SportsFacility getSportsFacilityById(Long sportsFacilityId);
    List<SportsFacility> getSportsFacilityByOwnerId(Long ownerId);
    SportsReservation getReservationSummaryBySportsFacilityId(Long sportsFacilityId, Date startDate, Date endDate);
    Object deleteSportsFacilityById(Long sportsFacilityId);
}
