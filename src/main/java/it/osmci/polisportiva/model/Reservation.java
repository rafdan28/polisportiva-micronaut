package it.osmci.polisportiva.model;

import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Reservation.findAll",
                query = "select r from Reservation r " +
                        "where " +
                        "(:state                          is null or :state               = r.reservationStatus)               and " +
                        "(:sport                          is null or :sport               = r.sportsField.sport)               and " +
                        "(cast(:createdAt as date)        is null or :createdAt           = r.createdAt)                       and " +
                        "(cast(:startDate as date)        is null or :startDate           <= r.dateTimeRange.startDateTime)    and " +
                        "(cast(:endDate as date)          is null or :endDate             >= r.dateTimeRange.endDateTime)      and " +
                        "(:price                          is null or :price               = r.price)                           and " +
                        "(:sportsFieldId                  is null or :sportsFieldId       = r.sportsField.id)                  and " +
                        "(:sportsFacilityId               is null or :sportsFacilityId    = r.sportsField.sportsFacility.id)"
        ),
        @NamedQuery(
                name = "Reservation.generateSportsReservationsReportForSportsFacility",
                query = "select r.sportsField.sport as sport, " +
                        "coalesce(count(r.id), 0) as totalReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then 1 else 0 end), 0)       as acceptedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'REJECTED') then 1 else 0 end), 0)       as rejectedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'PENDING')  then 1 else 0 end), 0)       as pendingReservations,  " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then r.price else 0 end), 0) as totalRevenue          " +
                        "from Reservation r where r.sportsField.sportsFacility.id = :sportsFacilityId                     and      " +
                        "(cast(:startDate as date)        is null or :startDate           <= r.dateTimeRange.startDateTime)     and      " +
                        "(cast(:endDate as date)          is null or :endDate             >= r.dateTimeRange.endDateTime)                " +
                        "group by sport"
        ),
        @NamedQuery(
                name = "Reservation.generateSportsFieldReservationsReportForSportsField",
                query = "select r.sportsField.sport as sport, " +
                        "coalesce(count(r.id), 0) as totalReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then 1 else 0 end), 0)       as acceptedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'REJECTED') then 1 else 0 end), 0)       as rejectedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'PENDING')  then 1 else 0 end), 0)       as pendingReservations,  " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then r.price else 0 end), 0) as totalRevenue          " +
                        "from Reservation r where r.sportsField.id = :sportsFieldId                                            and " +
                        "(cast(:startDate as date)         is null or :startDate           <= r.dateTimeRange.startDateTime)         and " +
                        "(cast(:endDate as date)           is null or :endDate             >= r.dateTimeRange.endDateTime)               " +
                        "group by sport"
        ),
        @NamedQuery(
                name = "Reservation.generateSportsReservationsReportForAllSportsFacility",
                query = "select r.sportsField.sportsFacility.id as sportsFacilityId, " +
                        "r.sportsField.sport as sport, " +
                        "coalesce(count(r.id), 0) as totalReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then 1 else 0 end), 0)       as acceptedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'REJECTED') then 1 else 0 end), 0)       as rejectedReservations, " +
                        "coalesce(sum(case when (r.reservationStatus = 'PENDING')  then 1 else 0 end), 0)       as pendingReservations,  " +
                        "coalesce(sum(case when (r.reservationStatus = 'ACCEPTED') then r.price else 0 end), 0) as totalRevenue          " +
                        "from Reservation r where " +
                        "(cast(:startDate as date)        is null or :startDate           <= r.dateTimeRange.startDateTime)          and " +
                        "(cast(:endDate as date)          is null or :endDate             >= r.dateTimeRange.endDateTime)                " +
                        "group by sportsFacilityId, sport"
        )
})
public class Reservation {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.PENDING;

    @NotNull
    @Embedded
    private DateTimeRange dateTimeRange;

    @OneToOne(
            mappedBy = "reservation",
            cascade = CascadeType.PERSIST
    )
    private ReservationRating rating;

    private float price;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @NotNull
    @ManyToOne
    private User owner;

    @NotNull
    @ManyToOne
    private SportsField sportsField;

    public Reservation(final DateTimeRange dateTimeRange, final float price, final User owner) {
        setDateTimeRange(dateTimeRange);
        setPrice(price);
        setOwner(owner);
    }

    public Reservation() {

    }

    public void setDateTimeRange(final DateTimeRange dateTimeRange) {
        Objects.requireNonNull(dateTimeRange);
        this.dateTimeRange = dateTimeRange;
    }

    public void setPrice(final float price) {
        if (price < 0) {
            throw new IllegalArgumentException("The price must be greater than zero!");
        }
        this.price = price;
    }

    public void setOwner(final User owner) {
        Objects.requireNonNull(owner);
        this.owner = owner;
    }

    public void setRating(final ReservationRating rating) {
        Objects.requireNonNull(rating);
        if (rating.getReservation() != null) {
            throw new IllegalArgumentException("This RatingEntity already has a reservation assigned!");
        }
        rating.setReservation(this);
        this.rating = rating;
    }

    public void setSportsField(final SportsField sportsField) {
        Objects.requireNonNull(sportsField);
        this.sportsField = sportsField;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public DateTimeRange getDateTimeRange() {
        return dateTimeRange;
    }

    public ReservationRating getRating() {
        return rating;
    }

    public float getPrice() {
        return price;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public SportsField getSportsField() {
        return sportsField;
    }


}