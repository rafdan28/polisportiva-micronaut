package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.SportsField;

import java.util.List;

public interface SportsFieldService {
    SportsField getSportsFieldById(Long sportsFieldId);

    List<SportsField> getSportsFields(Long filterByOwnerId, String sport);
}
