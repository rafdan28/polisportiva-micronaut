package it.osmci.polisportiva.model;

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

    @Column(nullable = false)
    private ZonedDateTime startDateTime;

    @Column(nullable = false)
    private ZonedDateTime endDateTime;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationStatus state = ReservationStatus.PENDING;

    @OneToOne(mappedBy = "reservation", cascade  = CascadeType.PERSIST)
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