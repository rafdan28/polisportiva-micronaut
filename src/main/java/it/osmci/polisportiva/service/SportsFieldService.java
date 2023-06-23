package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.SportsField;

import java.util.List;

public interface SportsFieldService {
    SportsField createSportsField(SportsField sportsField);
    List<SportsField> findAll();
    SportsField getSportsFieldById(Long sportsFieldId);
    List<SportsField> getSportsFieldsByOwnerIdBySport(Long ownerId, String sport);
    void deleteSportsFieldById(Long sportsFieldId);
}
