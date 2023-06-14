package it.osmci.polisportiva.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="Utente")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

//    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST})
//    @org.hibernate.annotations.OnDelete(
//            action = org.hibernate.annotations.OnDeleteAction.CASCADE
//    )
//    private Set<BillingDetailsEntity> allBillingDetails = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Set<SportsFacility> sportsFacilities = new HashSet<>();

    @NotBlank
    @NotNull
    @Column(name = "fiscalCode", unique = true)
    private String fiscalCode;

    @NotBlank
    @NotNull
    @Email(message = "Email should be valid", regexp = "^[\\w.\\.]+@([\\w.]+\\.)+[\\w.]{2,4}$")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    @Embedded
    private Address address;

    @CreationTimestamp
    private LocalDateTime registeredOn;

//    public boolean addBillingDetails(BillingDetailsEntity billingDetails) {
//        Objects.requireNonNull(billingDetails);
//        billingDetails.setOwner(this);
//        return allBillingDetails.add(billingDetails);
//    }
//
    public boolean addSportsFacility(SportsFacility sportsFacility) {
        Objects.requireNonNull(sportsFacility);
        sportsFacility.setOwner(this);
        return sportsFacilities.add(sportsFacility);
    }


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

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }
}