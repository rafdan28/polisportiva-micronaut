package it.osmci.polisportiva.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@Entity
public class ReservationRating {
    @Id
    @GeneratedValue(generator = "reservationKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
        name = "reservationKeyGenerator",
        strategy = "foreign",
        parameters =
            @org.hibernate.annotations.Parameter(
                name = "property", value = "reservation"
            )
    )
    private Long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Reservation reservation;

    @NotNull
    private float score;

    @NotNull
    private String description;

    public ReservationRating(final Reservation reservation, final float score, final String description) {
        setReservation(reservation);
        setScore(score);
        setDescription(description);
    }

    public void setReservation(final Reservation reservation) {
        Objects.requireNonNull(reservation);
        if (reservation.getRating() != null) {
            throw new IllegalArgumentException("This reservation already has a rating assigned!");
        }
        this.reservation = reservation;
    }

    public void setDescription(final String description) {
        Objects.requireNonNull(description);
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

}
