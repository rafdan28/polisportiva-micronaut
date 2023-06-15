package it.osmci.polisportiva.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZonedDateTime;
import java.util.Objects;

@org.hibernate.annotations.Immutable
@Embeddable
public class DateTimeRange {
    @Column(nullable = false)
    private ZonedDateTime startDateTime;

    @Column(nullable = false)
    private ZonedDateTime endDateTime;

    public DateTimeRange() {

    }


    public DateTimeRange(final ZonedDateTime startDateTime, final ZonedDateTime endDateTime) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        if (startDateTime.compareTo(endDateTime) >= 0) {
            throw new IllegalArgumentException("The starting date must be less than the ending date!");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
