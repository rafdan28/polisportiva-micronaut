package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.SportsField;

import java.util.List;

@Repository
public interface SportsFieldRepository extends JpaRepository<SportsField, Long> {
    @Query("SELECT s FROM SportsField s WHERE :ownerId = s.sportsFacility.owner.id")
    List<SportsField> getSportsFieldsByOwnerId(Long ownerId);
    @Query("SELECT s FROM SportsField s WHERE :sport = s.sport")
    List<SportsField> getSportsFieldsBySport(String sport);
    @Query("SELECT s FROM SportsField s WHERE (:sport = s.sport) AND (:ownerId = s.sportsFacility.owner.id)")
    List<SportsField> getSportsFieldsByOwnerIdBySport(Long ownerId, String sport);
}
