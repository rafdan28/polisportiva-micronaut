package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.ReservationRating;

@Repository
public interface ReservationRatingRepository extends JpaRepository<ReservationRating, Long> {
}
