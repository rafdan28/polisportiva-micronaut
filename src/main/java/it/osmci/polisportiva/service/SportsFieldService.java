package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.SportsField;

public interface SportsFieldService {
    SportsField getSportsFieldById(Long sportsFieldId);

    SportsField getSportsFields(Long filterByOwnerId, String sport);
}
