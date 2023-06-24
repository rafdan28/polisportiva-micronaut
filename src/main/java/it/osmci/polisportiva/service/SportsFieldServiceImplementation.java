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
        List<SportsField> sportsFieldList =  sportsFieldRepository.findAll();
        if(sportsFieldList.size() != 0) return sportsFieldList;
        else throw new ResourceNotFoundException("There are no sports fields present.");
    }

    @Override
    public SportsField getSportsFieldById(Long sportsFieldId) {
        return sportsFieldRepository.findById(sportsFieldId).orElseThrow(() -> new ResourceNotFoundException("There is no sports field with this id!"));
    }

    @Override
    public List<SportsField> getSportsFieldsByOwnerId(Long ownerId) {
        return sportsFieldRepository.getSportsFieldsByOwnerId(ownerId);
    }

    @Override
    public List<SportsField> getSportsFieldsBySport(String sport) {
        return sportsFieldRepository.getSportsFieldsBySport(sport);
    }

    @Override
    public List<SportsField> getSportsFieldsByOwnerIdBySport(Long ownerId, String sport) {
        try {
            return sportsFieldRepository.getSportsFieldsByOwnerIdBySport(ownerId, sport);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Object deleteSportsFieldById(Long sportsFieldId) {
        Objects.requireNonNull(sportsFieldId);
        if(sportsFieldRepository.existsById(sportsFieldId)){
            sportsFieldRepository.deleteById(sportsFieldId);
            return "This sport field id has been deleted";
        }
        else throw new ResourceNotFoundException("This id doesn't identify any sport field!");
    }
}
