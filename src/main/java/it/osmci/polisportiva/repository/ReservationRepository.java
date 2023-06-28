package it.osmci.polisportiva.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import it.osmci.polisportiva.model.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r JOIN SportsField sf ON r.sportsField.id=sf.id " +
            "WHERE (sf.sportsFacility.id=:sportsFacilityId) and (CAST(r.startDateTime AS date)>=:startDate) and (CAST(r.startDateTime AS date)<=:endDate)")
    List<Reservation> getReservationBySportsFacilityId(Long sportsFacilityId, Date startDate, Date endDate);

    @Query("SELECT r FROM Reservation r JOIN SportsField sf ON r.sportsField.id=sf.id " +
            "WHERE (sf.sportsFacility.id=:sportsFacilityId)")
    List<Reservation> getReservationBySportsFacilityId(Long sportsFacilityId);
}
