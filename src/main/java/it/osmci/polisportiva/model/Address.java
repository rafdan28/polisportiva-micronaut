package it.osmci.polisportiva.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
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
}
