package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.SportsFacility;

import java.util.List;

@Repository
public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {
    @Query("SELECT sf FROM SportsFacility AS sf JOIN Address AS af ON sf.address.id=af.id JOIN User u ON sf.owner.id=u.id WHERE sf.owner.id=:ownerId")
    List<SportsFacility> getSportsFacilityByOwnerId(Long ownerId);
}
