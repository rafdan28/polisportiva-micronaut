package it.osmci.polisportiva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_USERS")
    @SequenceGenerator(name = "ID_USERS", sequenceName = "ID_GENERATOR_USERS", allocationSize = 1)
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
//    @Email(message = "Email should be valid", regexp = "^[\\w.\\.]+@([\\w.]+\\.)+[\\w.]{2,4}$")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Column(name = "fiscalCode", unique = true)
    private String fiscalCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SportsFacility> sportsFacility = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SportsField> sportsFields = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Reservation> reservationList = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address homeAddress) {
        this.address = homeAddress;
    }

    public List<SportsFacility> getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(List<SportsFacility> sportsFacility) {
        this.sportsFacility = sportsFacility;
    }

    public List<SportsField> getSportsFields() {
        return sportsFields;
    }

    public void setSportsFields(List<SportsField> sportsFields) {
        this.sportsFields = sportsFields;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}