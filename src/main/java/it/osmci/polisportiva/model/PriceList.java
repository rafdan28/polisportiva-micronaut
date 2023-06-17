package it.osmci.polisportiva.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.LinkedList;
import java.util.List;

@Entity
public class PriceList {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
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
