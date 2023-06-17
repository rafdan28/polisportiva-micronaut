package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.SportsFacility;

@Repository
public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {

}
