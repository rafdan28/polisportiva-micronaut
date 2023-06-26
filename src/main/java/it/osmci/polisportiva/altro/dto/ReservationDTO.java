package it.osmci.polisportiva.altro.dto;

import io.micronaut.core.annotation.Introspected;

import java.time.ZonedDateTime;

@Introspected
public class ReservationDTO {
    private Long sportsFieldId;
    private Long ownerId;
    private DateRange dateRange;

    public static class DateRange {
        private ZonedDateTime startDate;
        private ZonedDateTime endDate;

        public ZonedDateTime getStartDate() {
            return startDate;
        }

        public void setStartDate(ZonedDateTime startDate) {
            this.startDate = startDate;
        }

        public ZonedDateTime getEndDate() {
            return endDate;
        }

        public void setEndDate(ZonedDateTime endDate) {
            this.endDate = endDate;
        }
    }

    public Long getSportsFieldId() {
        return sportsFieldId;
    }

    public void setSportsFieldId(Long sportsFieldId) {
        this.sportsFieldId = sportsFieldId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }


}
