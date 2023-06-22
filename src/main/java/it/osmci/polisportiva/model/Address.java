package it.osmci.polisportiva.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ADDRESS")
    @SequenceGenerator(name = "ID_ADDRESS", sequenceName = "ID_GENERATOR_ADDRESS", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 64)
    private String state;

    @NotNull
    @Column(nullable = false, length = 64)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String streetName;

    @NotNull
    @Column(nullable = false, length = 8)
    private String streetNumber;

    @NotNull
    @Column(nullable = false, length = 16)
    private String postcode;

//    @OneToMany(mappedBy = "address")
//    private List<User> users;
//
//    @OneToMany(mappedBy = "address")
//    private List<SportsFacility> sportsFacility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public List<SportsFacility> getSportsFacility() {
//        return sportsFacility;
//    }
//
//    public void setSportsFacility(List<SportsFacility> sportsFacility) {
//        this.sportsFacility = sportsFacility;
//    }
}
