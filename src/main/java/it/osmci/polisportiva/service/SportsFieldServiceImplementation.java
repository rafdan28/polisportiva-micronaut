package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.repository.SportsFieldRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class SportsFieldServiceImplementation implements SportsFieldService{

    @Inject
    private SportsFieldRepository sportsFieldRepository;

    @Override
    public SportsField createSportsField(SportsField sportsField) {
        Objects.requireNonNull(sportsField);
        return sportsFieldRepository.save(sportsField);
    }

    @Override
    public List<SportsField> findAll() {
        return sportsFieldRepository.findAll();
    }

    @Override
    public SportsField getSportsFieldById(Long sportsFieldId) {
        return sportsFieldRepository.findById(sportsFieldId).orElseThrow(() -> new ResourceNotFoundException("There is no sports field with this id!"));
    }

    @Override
    public void deleteSportsFieldById(Long sportsFieldId) {
        sportsFieldRepository.deleteById(sportsFieldId);
    }

//    @Override
//    public List<SportsField> getSportsFields(Long filterByOwnerId, String sport) {
//        return sportsFieldRepository.getSportsFields(filterByOwnerId, sport);
//    }


}
