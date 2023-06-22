package it.osmci.polisportiva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class SportsFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SPORTSFACILITY")
    @SequenceGenerator(name = "ID_SPORTSFACILITY", sequenceName = "ID_GENERATOR_SPORTSFACILITY", allocationSize = 1)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int totalSportsField;

    @NotNull
    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "sportsFacility", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SportsField> sportsFields = new LinkedList<>();

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<SportsField> getSportsFields() {
        return sportsFields;
    }

    public void setSportsFields(List<SportsField> sportsFields) {
        this.sportsFields = sportsFields;
    }
}
