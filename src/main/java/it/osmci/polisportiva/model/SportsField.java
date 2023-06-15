package it.osmci.polisportiva.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sport")
public class SportsField {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @Formula(value = "sport")
    private String sport;

    private boolean isIndoor;

    private double rating;

    @NotNull
    @OneToOne(
            mappedBy = "sportsField",
            cascade = {CascadeType.PERSIST}
    )
    private SportsFieldPriceList priceList;

    @NotNull
    @ManyToOne
    private SportsFacility sportsFacility;

    protected SportsField(final String name, final boolean isIndoor) {
        Objects.requireNonNull(name);
        this.name = name;
        this.isIndoor = isIndoor;
    }

    public SportsField() {

    }

    public void setPriceList(final SportsFieldPriceList priceList) {
        Objects.requireNonNull(priceList);
        this.priceList = priceList;
        priceList.setSportsField(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SportsField that = (SportsField) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public SportsFieldPriceList getPriceList() {
        return priceList;
    }

    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }


}