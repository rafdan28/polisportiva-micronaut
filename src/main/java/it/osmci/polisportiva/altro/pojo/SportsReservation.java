package it.osmci.polisportiva.altro.pojo;

import java.util.List;

public class SportsReservation {
    private Long sportsFacilityId;

    private String startDateTime;

    private String endDateTime;

    private String createdAt;

    private List<SportsReservationReport> sportsReservationReport;

    public SportsReservation(Long sportsFacilityId, String startDateTime, String endDateTime, String createdAt, List<SportsReservationReport> sportsReservationReport) {
        this.sportsFacilityId = sportsFacilityId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdAt = createdAt;
        this.sportsReservationReport = sportsReservationReport;
    }

    public SportsReservation(Long sportsFacilityId, String createdAt, List<SportsReservationReport> sportsReservationReport) {
        this.sportsFacilityId = sportsFacilityId;
        this.createdAt = createdAt;
        this.sportsReservationReport = sportsReservationReport;
    }

    public Long getSportsFacilityId() {
        return sportsFacilityId;
    }

    public void setSportsFacilityId(Long sportsFacilityId) {
        this.sportsFacilityId = sportsFacilityId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<SportsReservationReport> getSportsReservationReport() {
        return sportsReservationReport;
    }

    public void setSportsReservationReport(List<SportsReservationReport> sportsReservationReport) {
        this.sportsReservationReport = sportsReservationReport;
    }
}
