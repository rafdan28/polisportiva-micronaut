package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.SportsField;

import java.util.List;

@Repository
public interface SportsFieldRepository extends JpaRepository<SportsField, Long> {
    @Query("SELECT s FROM SportsField s WHERE (:sport IS NULL OR :sport = s.sport) AND (:ownerId IS NULL OR :ownerId = s.sportsFacility.owner.id)")
    List<SportsField> getSportsFieldsByOwnerIdBySport(Long ownerId, String sport);
}
