package it.osmci.polisportiva.altro.pojo;

public class SportsReservationReport {

    private long totalReservations;

    private String sport;

    private double totalRevenue;

    private long rejectedReservations;

    private long acceptedReservations;

    private long pendingReservations;

    public SportsReservationReport(long totalReservations, String sport, double totalRevenue, long rejectedReservations, long acceptedReservations, long pendingReservations) {
        this.totalReservations = totalReservations;
        this.sport = sport;
        this.totalRevenue = totalRevenue;
        this.rejectedReservations = rejectedReservations;
        this.acceptedReservations = acceptedReservations;
        this.pendingReservations = pendingReservations;
    }

    public long getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(long totalReservations) {
        this.totalReservations = totalReservations;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getRejectedReservations() {
        return rejectedReservations;
    }

    public void setRejectedReservations(long rejectedReservations) {
        this.rejectedReservations = rejectedReservations;
    }

    public long getAcceptedReservations() {
        return acceptedReservations;
    }

    public void setAcceptedReservations(long acceptedReservations) {
        this.acceptedReservations = acceptedReservations;
    }

    public long getPendingReservations() {
        return pendingReservations;
    }

    public void setPendingReservations(long pendingReservations) {
        this.pendingReservations = pendingReservations;
    }
}
