package it.osmci.polisportiva.model;

import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Entity
@NamedQueries({
    @NamedQuery(
        name = "SportsFacilityEntity.findAllByTotalNumberSportsFieldsBetween",
        query = "SELECT sf FROM SportsFacility sf WHERE sf.totalSportsField > :min AND sf.totalSportsField < :max"
    ),
    @NamedQuery(
        name = "SportsFacilityEntity.findAllByOwnerIdAndTotalNumberSportsFieldsBetween",
        query = "SELECT sf FROM SportsFacility sf WHERE sf.owner.id = :ownerId " +
                "AND sf.totalSportsField > :min AND sf.totalSportsField < :max"
    )
})
public class SportsFacility {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @ManyToOne
    private User owner;

    @OneToMany(
        mappedBy = "sportsFacility",
        cascade = {CascadeType.PERSIST}
    )
    @OnDelete(
        action = OnDeleteAction.CASCADE
    )
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<SportsField> sportsFields = new HashSet<>();

    private int totalSportsField;

    public SportsFacility(final String name, final String phone) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(phone);
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SportsFacility that = (SportsFacility) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addSportsField(final SportsField sportsField) {
        Objects.requireNonNull(sportsField);
        sportsFields.add(sportsField);
        totalSportsField++;
        sportsField.setSportsFacility(this);
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

    public Set<SportsField> getSportsFields() {
        return sportsFields;
    }

    public void setSportsFields(Set<SportsField> sportsFields) {
        this.sportsFields = sportsFields;
    }

    public int getTotalSportsField() {
        return totalSportsField;
    }

    public void setTotalSportsField(int totalSportsField) {
        this.totalSportsField = totalSportsField;
    }


}
