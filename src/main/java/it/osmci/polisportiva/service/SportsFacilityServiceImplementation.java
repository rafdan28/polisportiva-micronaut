package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.repository.SportsFacilityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class SportsFacilityServiceImplementation implements SportsFacilityService{
    @Inject
    private SportsFacilityRepository sportsFacilityRepository;

    @Override
    public SportsFacility createSportsFacility(SportsFacility sportsFacility) {
        Objects.requireNonNull(sportsFacility);
        return sportsFacilityRepository.save(sportsFacility);
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
    public void deleteSportsFacilityById(Long sportsFacilityId) {
        sportsFacilityRepository.deleteById(sportsFacilityId);
    }
}
