package it.osmci.polisportiva.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RESERVATION")
    @SequenceGenerator(name = "ID_RESERVATION", sequenceName = "ID_GENERATOR_RESERVATION", allocationSize = 1)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(nullable = false)
    private ZonedDateTime startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(nullable = false)
    private ZonedDateTime endDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationStatus state = ReservationStatus.PENDING;

    @OneToOne(mappedBy = "reservation", cascade  = CascadeType.PERSIST)
    @JsonManagedReference
    private ReservationRating rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sports_field_id", nullable = false)
    private SportsField sportsField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ReservationStatus getState() {
        return state;
    }

    public void setState(ReservationStatus state) {
        this.state = state;
    }

    public ReservationRating getRating() {
        return rating;
    }

    public void setRating(ReservationRating rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SportsField getSportsField() {
        return sportsField;
    }

    public void setSportsField(SportsField sportsField) {
        this.sportsField = sportsField;
    }
}