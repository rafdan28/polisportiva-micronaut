package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.repository.SportsFieldRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SportsFieldServiceImplementation implements SportsFieldService{

    @Inject
    private SportsFieldRepository sportsFieldRepository;

    @Override
    public SportsField getSportsFieldById(Long sportsFieldId) {
        return sportsFieldRepository.findById(sportsFieldId).orElseThrow(() -> new ResourceNotFoundException("There is no sports field with this id!"));
    }
}
