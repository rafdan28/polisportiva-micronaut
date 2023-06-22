package it.osmci.polisportiva.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PRICE")
    @SequenceGenerator(name = "ID_PRICE", sequenceName = "ID_GENERATOR_PRICE", allocationSize = 1)
    private Long id;

    @Min(value = 0)
    private float pricePerHour;

//    @OneToMany
//    private List<SportsField> sportsFields = new LinkedList<>();

    public PriceList(Long id, float pricePerHour) {
        this.id = id;
        this.pricePerHour = pricePerHour;
    }

    public PriceList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

//    public List<SportsField> getSportsFields() {
//        return sportsFields;
//    }
//
//    public void setSportsFields(List<SportsField> sportsFields) {
//        this.sportsFields = sportsFields;
//    }
}
