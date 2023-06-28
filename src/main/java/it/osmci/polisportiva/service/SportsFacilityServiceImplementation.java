package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.pojo.SportsReservation;
import it.osmci.polisportiva.altro.pojo.SportsReservationReport;
import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.Reservation;
import it.osmci.polisportiva.model.SportsFacility;
import it.osmci.polisportiva.model.SportsField;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.repository.SportsFacilityRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Singleton
public class SportsFacilityServiceImplementation implements SportsFacilityService{
    @Inject
    private SportsFacilityRepository sportsFacilityRepository;

    @Inject
    private SportsFieldService sportsFieldService;

    @Inject
    private ReservationService reservationService;

    @Inject
    private UserService userService;

    @Override
    public SportsFacility createSportsFacility(SportsFacility sportsFacility) {
        Objects.requireNonNull(sportsFacility);
        return sportsFacilityRepository.save(sportsFacility);
    }

    @Override
    public SportsField createSportsFieldBySportsFacility(Long sportsFacilityId, SportsField sportsField) {
        try {
            SportsFacility sportsFacility = getSportsFacilityById(sportsFacilityId);
            if(sportsFacility != null){
                sportsField.setSportsFacility(sportsFacility);
                sportsField.setOwner(sportsFacility.getOwner());
                return sportsFieldService.createSportsField(sportsField);
            }
            return null;
        }
        catch (ResourceNotFoundException e){
            return null;
        }
    }

    @Override
    public List<SportsFacility> findAll() {
        List<SportsFacility> sportsFacilityList =  sportsFacilityRepository.findAll();
        if(sportsFacilityList.size() != 0) return sportsFacilityList;
        else throw new ResourceNotFoundException("There are no sports facilities present.");
    }

    @Override
    public SportsFacility getSportsFacilityById(Long sportsFacilityId) {
        return sportsFacilityRepository.findById(sportsFacilityId).orElseThrow(() -> new ResourceNotFoundException("There is no sports facility with this id!"));
    }

    @Override
    public List<SportsFacility> getSportsFacilityByOwnerId(Long ownerId) {
        User user = userService.getUserById(ownerId);
        if(user != null){
            return sportsFacilityRepository.getSportsFacilityByOwnerId(ownerId);
        }
        else throw new ResourceNotFoundException("This user id doesn't identify any user!");
    }

    @Override
    public SportsReservation getReservationSummaryBySportsFacilityId(Long sportsFacilityId, Date startDate, Date endDate) {
        SportsFacility sportsFacility = getSportsFacilityById(sportsFacilityId);
        List<Reservation> reservations = reservationService.getReservationBySportsFacilityId(sportsFacilityId, startDate, endDate);
        return getSportsReservation(sportsFacilityId, sportsFacility, reservations);
    }

    @Override
    public SportsReservation getReservationSummaryBySportsFacilityId(Long sportsFacilityId) {
        SportsFacility sportsFacility = getSportsFacilityById(sportsFacilityId);
        List<Reservation> reservations = reservationService.getReservationBySportsFacilityId(sportsFacilityId);
        return getSportsReservation(sportsFacilityId, sportsFacility, reservations);
    }

    @Override
    public Object deleteSportsFacilityById(Long sportsFacilityId) {
        Objects.requireNonNull(sportsFacilityId);
        if(sportsFacilityRepository.existsById(sportsFacilityId)){
            sportsFacilityRepository.deleteById(sportsFacilityId);
            return "This sport facility id has been deleted";
        }
        else throw new ResourceNotFoundException("This id doesn't identify any sport facility!");
    }

    private SportsReservation getSportsReservation(Long sportsFacilityId, SportsFacility sportsFacility, List<Reservation> reservations) {
        int[] volleyballInfo = new int[4];
        int[] soccerInfo = new int[4];
        int[] basketInfo = new int[4];
        int[] tennisInfo = new int[4];

        if(sportsFacility != null){
            for (Reservation reservation : reservations) {
                switch (reservation.getSportsField().getSport()) {
                    case "volleyball" -> {
                        volleyballInfo[0]++;
                        increaseByState(volleyballInfo, reservation.getState());
                    }
                    case "soccer" -> {
                        soccerInfo[0]++;
                        increaseByState(soccerInfo, reservation.getState());
                    }
                    case "basket" -> {
                        basketInfo[0]++;
                        increaseByState(basketInfo, reservation.getState());
                    }
                    case "tennis" -> {
                        tennisInfo[0]++;
                        increaseByState(tennisInfo, reservation.getState());
                    }
                }
            }
        }

        List<SportsReservationReport> sportsReservationReportList = new LinkedList<>();
        sportsReservationReportList.add(new SportsReservationReport(volleyballInfo[0],"volleyball", 0.0, volleyballInfo[1], volleyballInfo[2], volleyballInfo[3]));
        sportsReservationReportList.add(new SportsReservationReport(soccerInfo[0],"soccer", 0.0, soccerInfo[1], soccerInfo[2], soccerInfo[3]));
        sportsReservationReportList.add(new SportsReservationReport(basketInfo[0],"basket", 0.0, basketInfo[1], basketInfo[2], basketInfo[3]));
        sportsReservationReportList.add(new SportsReservationReport(tennisInfo[0],"tennis", 0.0, tennisInfo[1], tennisInfo[2], tennisInfo[3]));

        return new SportsReservation(sportsFacilityId, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()), sportsReservationReportList);
    }

    private void increaseByState(int[] array, ReservationStatus state) {
        switch (state) {
            case REJECTED -> array[1]++;
            case ACCEPTED -> array[2]++;
            case PENDING -> array[3]++;
        }
    }

}
