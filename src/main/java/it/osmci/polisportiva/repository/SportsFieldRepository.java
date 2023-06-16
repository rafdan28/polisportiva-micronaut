package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.SportsField;

import java.util.List;

@Repository
public interface SportsFieldRepository extends JpaRepository<SportsField, Long> {
    @Query(
            "select s " +
                    "from SportsField s " +
                    "where (:sport is null or :sport = s.sport) and (:ownerId is null or :ownerId = s.sportsFacility.id)"
    )
    List<SportsField> getSportsFields(Long ownerId, String sport);

}
