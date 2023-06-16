package it.osmci.polisportiva;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import it.osmci.polisportiva.altro.enumeration.ReservationStatus;
import it.osmci.polisportiva.altro.enumeration.SoccerFieldType;
import it.osmci.polisportiva.altro.enumeration.TennisFieldType;
import it.osmci.polisportiva.model.*;
import it.osmci.polisportiva.repository.ReservationRepository;
import it.osmci.polisportiva.repository.SportsFacilityRepository;
import it.osmci.polisportiva.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.ZonedDateTime;
import java.util.List;

@Singleton
public class ApplicationStartupListener implements ApplicationEventListener<ServerStartupEvent> {
    @Inject
    private UserRepository userRepository;

    @Inject
    private SportsFacilityRepository sportsFacilityRepository;

    @Inject
    private ReservationRepository reservationRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        //initDatabase();
    }

    void initDatabase() {
        Address address = new Address();
        address.setStreetNumber("30");
        address.setStreetName("Via Molino");
        address.setCity("Acerra (NA)");
        address.setPostcode("80011");
        address.setState("Italia");

        User user = new User();
        user.setUsername("usciao");
        user.setPassword("ciao");
        user.setFirstName("aaaa");
        user.setLastName("bbbbbb");
        user.setFiscalCode("abcdefgh564");
        user.setEmail("aabb.g@gmail.com");
        user.setAddress(address);

        SportsFacility sportsFacility = new SportsFacility("Sports Club 2022", "666");
        sportsFacility.setAddress(address);
        user.addSportsFacility(sportsFacility);
        sportsFacility.setOwner(user);

        SportsFacility sportsFacility1 = new SportsFacility("New Sports Club 2022", "777");
        sportsFacility1.setAddress(address);
        sportsFacility1.setOwner(user);

        SportsField sportsField = new SoccerField("Eden", SoccerFieldType.ELEVEN_A_SIDE, true);
        SportsField sportsField1 = new BasketballField("Basket Field", false);
        SportsField sportsField2 = new TennisField("Tennis Field", TennisFieldType.CEMENT, false);
        sportsField.setSportsFacility(sportsFacility);
        sportsField.setPriceList(new SportsFieldPriceList(0, 0));
        sportsField1.setSportsFacility(sportsFacility1);
        sportsField1.setPriceList(new SportsFieldPriceList(0, 0));
        sportsField2.setSportsFacility(sportsFacility1);
        sportsField2.setPriceList(new SportsFieldPriceList(0, 0));

        sportsFacility.getSportsFields().add(sportsField);
        sportsFacility1.getSportsFields().add(sportsField1);
        sportsFacility1.getSportsFields().add(sportsField2);

        SportsFieldPriceList sportsFieldPriceListEntity = new SportsFieldPriceList(75.0f, 5.0f);
        sportsField.setPriceList(sportsFieldPriceListEntity);

        var dateTimeRange = new DateTimeRange(ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
        var dateTimeRange2 = new DateTimeRange(ZonedDateTime.now().minusDays(3), ZonedDateTime.now().minusDays(2));
        List<Reservation> reservationList = List.of(
                new Reservation(dateTimeRange, 10f, user),
                new Reservation(dateTimeRange, 10f, user),
                new Reservation(dateTimeRange, 10f, user),
                new Reservation(dateTimeRange2, 30f, user)
        );
        reservationList.get(0).setSportsField(sportsField);
        reservationList.get(1).setSportsField(sportsField);
        reservationList.get(2).setSportsField(sportsField1);
        reservationList.get(3).setReservationStatus(ReservationStatus.ACCEPTED);
        reservationList.get(3).setSportsField(sportsField2);

        ReservationRating reservationRating = new ReservationRating();
        reservationRating.setDescription("Wonderful");
        reservationRating.setScore(4);

        ReservationRating reservationRating1 = new ReservationRating();
        reservationRating1.setDescription("Beautiful");
        reservationRating1.setScore(5);

        reservationList.get(0).setRating(reservationRating);
        reservationList.get(1).setRating(reservationRating1);

        ReservationRating reservationRating2 = new ReservationRating();
        reservationRating2.setDescription(" ");
        reservationRating2.setScore(3);

        reservationList.get(3).setRating(reservationRating2);

        userRepository.save(user);
        sportsFacilityRepository.save(sportsFacility1);
        sportsFacilityRepository.save(sportsFacility);
        reservationRepository.saveAll(reservationList);
    }


}