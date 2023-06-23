package it.osmci.polisportiva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class SportsField {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SPORTSFIELD")
    @SequenceGenerator(name = "ID_SPORTSFIELD", sequenceName = "ID_GENERATOR_SPORTSFIELD", allocationSize = 1)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    private String sport;

    private boolean isIndoor;

    @Column(name = "soccer_field_type")
    private String soccerFieldType;

    @Column(name = "tennis_field_type")
    private String tennisFieldType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sports_facility_id", nullable = false)
    private SportsFacility sportsFacility;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "price_list_id", nullable = false)
    private PriceList priceList;

    @JsonIgnore
    @OneToMany(mappedBy = "sportsField", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Reservation> reservationList = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    public String getSoccerFieldType() {
        return soccerFieldType;
    }

    public void setSoccerFieldType(String soccerFieldType) {
        this.soccerFieldType = soccerFieldType;
    }

    public String getTennisFieldType() {
        return tennisFieldType;
    }

    public void setTennisFieldType(String tennisFieldType) {
        this.tennisFieldType = tennisFieldType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}