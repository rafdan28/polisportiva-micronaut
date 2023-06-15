package it.osmci.polisportiva.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class SportsFieldPriceList {
    @Id
    @GeneratedValue(generator = "sportsFieldKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
        name = "sportsFieldKeyGenerator",
        strategy = "foreign",
        parameters =
            @org.hibernate.annotations.Parameter(
                name = "property", value = "sportsField"
            )
    )
    private Long id;

    @NotNull
    @OneToOne
    @PrimaryKeyJoinColumn
    @org.hibernate.annotations.OnDelete(
        action = org.hibernate.annotations.OnDeleteAction.CASCADE
    )
    private SportsField sportsField;

    @Min(value = 0)
    private float pricePerHour;

    @Min(value = 0)
    private float priceIndoor;

    public SportsFieldPriceList(final float pricePerHour) {
        if (pricePerHour < 0) throw new IllegalArgumentException();
        this.pricePerHour = pricePerHour;
    }

    public SportsFieldPriceList(final float pricePerHour, final float priceIndoor) {
        this(pricePerHour);
        if (priceIndoor < 0) throw new IllegalArgumentException();
        this.priceIndoor = priceIndoor;
    }

    public SportsFieldPriceList() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SportsField getSportsField() {
        return sportsField;
    }

    public void setSportsField(SportsField sportsField) {
        this.sportsField = sportsField;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public float getPriceIndoor() {
        return priceIndoor;
    }

    public void setPriceIndoor(float priceIndoor) {
        this.priceIndoor = priceIndoor;
    }
}
