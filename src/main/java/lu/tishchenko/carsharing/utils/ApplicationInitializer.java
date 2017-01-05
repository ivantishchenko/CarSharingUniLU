package lu.tishchenko.carsharing.utils;

import lu.tishchenko.ejb.entity.Car;
import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.ejb.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ivan on 04.01.2017.
 */
@Singleton
@Startup
public class ApplicationInitializer {
    @Inject
    private Logger log;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {

        log.info("init app....");
        List<User> userList = em.createQuery("select u from User u").getResultList();

        if (userList.size() == 0) {
            createBaseTable();
        }

    }

    private void createBaseTable(){
        User tom = new User();
        tom.setName("tom");
        tom.setPassword("123");
        tom.setAdminRole(true);

        User ivan = new User();
        ivan.setName("ivan");
        ivan.setPassword("123");
        ivan.setAdminRole(true);

        User volker = new User();
        volker.setName("volker");
        volker.setPassword("123");
        volker.setAdminRole(false);

        Car car = new Car();
        car.setName("CarRepository");
        car.setInfo("This is my car " + car.getName());

        Car mb = new Car();
        mb.setName("MB");
        mb.setInfo("e550 " + mb.getName());

        Car bmw = new Car();
        bmw.setName("BMW");
        bmw.setInfo("x6 " + bmw.getName());

        Car vw = new Car();
        vw.setName("VW");
        vw.setInfo("GTI " + vw.getName());

        Trip milanBasel = new Trip();
        milanBasel.setName("Cool trip");
        milanBasel.setMaxCompanions(4);
        milanBasel.setFreePlaces(milanBasel.getMaxCompanions());
        milanBasel.setStartLocation("Milan");
        milanBasel.setStopLocation("Basel");

        Trip oxfordCambridge = new Trip();
        oxfordCambridge.setName("UK Ttrip");
        oxfordCambridge.setMaxCompanions(2);
        oxfordCambridge.setFreePlaces(oxfordCambridge.getMaxCompanions());
        oxfordCambridge.setStartLocation("Oxford");
        oxfordCambridge.setStopLocation("Cambridge");

        Trip benelux = new Trip();
        benelux.setName("Benelux trip");
        benelux.setMaxCompanions(3);
        benelux.setFreePlaces(benelux.getMaxCompanions());
        benelux.setStartLocation("Luxembourg");
        benelux.setStopLocation("Brussels");

        Trip berlinParis = new Trip();
        berlinParis.setName("Old school trip");
        berlinParis.setMaxCompanions(2);
        berlinParis.setFreePlaces(berlinParis.getMaxCompanions());
        berlinParis.setStartLocation("Berlin");
        berlinParis.setStopLocation("Paris");

        tom.setCar(car);
        ivan.setCar(mb);
        volker.setCar(bmw);

        car.setOwner(tom);
        mb.setOwner(ivan);
        bmw.setOwner(volker);


        List<Trip> myTrips = new ArrayList<Trip>();
        myTrips.add(milanBasel);
        tom.setTracks(myTrips);
        milanBasel.setOwner(tom);

        myTrips = new ArrayList<Trip>();
        myTrips.add(oxfordCambridge);
        myTrips.add(benelux);
        ivan.setTracks(myTrips);
        oxfordCambridge.setOwner(ivan);
        benelux.setOwner(ivan);
        myTrips = new ArrayList<Trip>();
        myTrips.add(berlinParis);
        volker.setTracks(myTrips);
        berlinParis.setOwner(volker);

        em.merge(tom);
        em.merge(ivan);
        em.merge(volker);

        List<User> users = em.createQuery("select u from User u").getResultList();
        List<Trip> tracks = em.createQuery("select t from Trip t").getResultList();

        User u = users.get(2);
        Trip t1 = tracks.get(1);
        Trip t2 = tracks.get(2);
        List<User> companions = new ArrayList<User>();
        List<Trip> reservedTracks = new ArrayList<Trip>();

        companions.add(u);
        reservedTracks.add(t1);
        reservedTracks.add(t2);
        u.setReservedTracks(reservedTracks);
        t1.setCompanions(companions);
        t2.setCompanions(companions);
        em.merge(u);
    }

    private void simpleSetUp() {
        User tom = new User();
        tom.setName("tom");
        tom.setPassword("123");
        tom.setAdminRole(true);

        User ivan = new User();
        ivan.setName("ivan");
        ivan.setPassword("123");
        ivan.setAdminRole(true);

        User volker = new User();
        volker.setName("volker");
        volker.setPassword("123");
        volker.setAdminRole(false);


        Car mb = new Car();
        mb.setName("MB");
        mb.setInfo("E550 " + mb.getName());

        Car bmw = new Car();
        bmw.setName("BMW");
        bmw.setInfo("X6 " + bmw.getName());

        Car vw = new Car();
        vw.setName("VW");
        vw.setInfo("Golf GTI " + vw.getName());


        ivan.setCar(bmw);
        tom.setCar(mb);
        volker.setCar(vw);

        bmw.setOwner(ivan);
        mb.setOwner(tom);
        vw.setOwner(volker);

    }

}