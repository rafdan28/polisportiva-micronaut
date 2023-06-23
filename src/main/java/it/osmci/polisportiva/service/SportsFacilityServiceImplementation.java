package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.repository.SportsFacilityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class SportsFacilityServiceImplementation implements SportsFacilityService{
    @Inject
    private SportsFacilityRepository sportsFacilityRepository;

    @Inject
    private SportsFieldService sportsFieldService;

    @Override
    public SportsFacility createSportsFacility(SportsFacility sportsFacility) {
        Objects.requireNonNull(sportsFacility);
        return sportsFacilityRepository.save(sportsFacility);
    }

    @Override
    public SportsField createSportsFieldBySportsFacility(Long sportsFacilityId, SportsField sportsField) {
        try {
            SportsFacility sportsFacility = getSportsFacilityById(sportsFacilityId);
            if(sportsFacility != null){
                sportsField.setSportsFacility(sportsFacility);
                sportsField.setUser(sportsFacility.getOwner());
                return sportsFieldService.createSportsField(sportsField);
            }
            return null;
        }
        catch (ResourceNotFoundException e){
            return null;
        }
    }

    @Override
    public List<SportsFacility> findAll() {
        return sportsFacilityRepository.findAll();
    }

    @Override
    public SportsFacility getSportsFacilityById(Long sportsFacilityId) {
        return sportsFacilityRepository.findById(sportsFacilityId).orElseThrow(() -> new ResourceNotFoundException("There is no sports facility with this id!"));
    }

    @Override
    public SportsFacility getSportsFacilityByOwnerId(Long ownerId) {
        return sportsFacilityRepository.getSportsFacilityByOwnerId(ownerId);
    }

    @Override
    public void deleteSportsFacilityById(Long sportsFacilityId) {
        sportsFacilityRepository.deleteById(sportsFacilityId);
    }
}
