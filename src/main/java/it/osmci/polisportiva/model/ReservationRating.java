package it.osmci.polisportiva.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ReservationRating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RESERVATIONRATING")
    @SequenceGenerator(name = "ID_RESERVATIONRATING", sequenceName = "ID_GENERATOR_RESERVATIONRATING", allocationSize = 1)
    private Long id;

    @NotNull
    private float rating;

    @NotNull
    private String description;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
