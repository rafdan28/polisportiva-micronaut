package it.osmci.polisportiva.altro;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import it.osmci.polisportiva.model.*;
import it.osmci.polisportiva.repository.SportsFacilityRepository;
import it.osmci.polisportiva.repository.SportsFieldRepository;
import it.osmci.polisportiva.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DataInitializer implements ApplicationEventListener<ServerStartupEvent> {
    @Inject
    private UserRepository userRepository;

    @Inject
    private SportsFacilityRepository sportsFacilityRepository;

    @Inject
    private SportsFieldRepository sportsFieldRepository;

    public void initializeData() {
        Address address = new Address();
        address.setState("Italia");
        address.setCity("Acerra");
        address.setStreetName("Via Molino");
        address.setStreetNumber("96");
        address.setPostcode("80011");

        User user = new User();
        user.setUsername("rafdan28");
        user.setPassword("ciao");
        user.setFirstName("Raffaele");
        user.setLastName("D'Anna");
        user.setFiscalCode("RDFWET98C11E281Y");
        user.setEmail("raffae.danna@studenti.unina.it");
        user.setAddress(address);

        Address addressSportFacility = new Address();
        addressSportFacility.setState("Italia");
        addressSportFacility.setCity("Napoli");
        addressSportFacility.setStreetName("Via Stadera");
        addressSportFacility.setStreetNumber("96");
        addressSportFacility.setPostcode("80026");

        SportsFacility sportsFacility = new SportsFacility();
        sportsFacility.setName("Sports Club");
        sportsFacility.setTotalSportsField(2);
        sportsFacility.setPhone("0815207485");
        sportsFacility.setAddress(addressSportFacility);
        sportsFacility.setOwner(user);

        PriceList priceList = new PriceList();
        priceList.setPricePerHour(5);

        SportsField sportsField = new SportsField();
        sportsField.setName("Field for Sports Club");
        sportsField.setSport("volleball");
        sportsField.setIndoor(true);
        sportsField.setPriceList(priceList);
        sportsField.setSportsFacility(sportsFacility);
        sportsField.setOwner(user);

        userRepository.save(user);
        sportsFacilityRepository.save(sportsFacility);
        sportsFieldRepository.save(sportsField);
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        initializeData();
    }
}
