package it.osmci.polisportiva.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class SportsFacility {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int totalSportsField;

    @NotNull
    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @OneToMany
//    private List<SportsField> sportsFields = new LinkedList<>();

    public SportsFacility(final String name, final String phone) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(phone);
        this.name = name;
        this.phone = phone;
    }

    public SportsFacility() {

    }

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

    public int getTotalSportsField() {
        return totalSportsField;
    }

    public void setTotalSportsField(int totalSportsField) {
        this.totalSportsField = totalSportsField;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<SportsField> getSportsFields() {
//        return sportsFields;
//    }
//
//    public void setSportsFields(List<SportsField> sportsFields) {
//        this.sportsFields = sportsFields;
//    }


}
