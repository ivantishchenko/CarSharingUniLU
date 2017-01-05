package lu.tishchenko.ejb.facade;

import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.entity.helper.AbstractDBObject;
import lu.tishchenko.ejb.facade.helper.AbstractDBObjectFacade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Ivan on 05.01.2017.
 */
@Named
@Stateless
public class TripFacade extends AbstractDBObjectFacade {

    @Override
    public AbstractDBObject saveOrUpdate(AbstractDBObject dbObject) {
        Trip track = (Trip) dbObject;
        if (track != null && track.getCompanions() != null
                && track.getMaxCompanions() - track.getCompanions().size() != track.getFreePlaces()) {
            for (int i = track.getCompanions().size() - 1;
                 track.getMaxCompanions() < track.getCompanions().size(); --i) {
                track.getCompanions().get(i).getReservedTracks().remove(track);
                track.getCompanions().remove(i);
            }
            track.setFreePlaces(track.getMaxCompanions() - track.getCompanions().size());
        }
        return super.saveOrUpdate(dbObject);
    }

    public List<Trip> getAllOwner(User owner) {
        try {
            return em.createQuery("select t from Trip t where t.deleted is null "
                    + "and t.owner=:owner").setParameter("owner", owner).getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
    public List<Trip> getAll(User loggedUser) {
        try {
            List<Trip> tracks = em.createQuery("select t from Trip t where t.deleted is null "
                    + "and t.freePlaces > 0").getResultList();
            if (loggedUser != null) {
                for (Trip track : loggedUser.getReservedTracks()) {
                    if (!tracks.contains(track)) {
                        tracks.add(track);
                    }
                }
            }
            return tracks;
        } catch (NoResultException nrEx) {
            return null;
        }
    }
/*
    public List<Trip> getAll() {
        try {
            return em.createQuery("select t from Trip t where t.deleted is null "
                    + "and t.freePlaces > 0").getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }*/

    public List<Trip> getTrackByDestination(String start, String stop){
        try {
            return em.createQuery("select t from Trip t where t.startLocation =:start " +
                    "and  t.stopLocation = :stop and t.deleted is null and t.freePlaces > 0")
                    .setParameter("start", start).setParameter("stop", stop).getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public List<Trip> getUserTracks(User user) {
        // I use eager fetch
        return user.getTracks();
    }

    public List<Trip> getUserReservedTracks(User user) {
        // I use eager fetch
        return user.getReservedTracks();
    }

    public Trip getTrackByName(String trackName) {
        try {
            return (Trip) em.createQuery("select t from Trip t where t.name = :name and t.deleted is null")
                    .setParameter("name", trackName).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public Trip getTrackByID(int trackID) {
        try {
            return (Trip) em.createQuery("select t from Trip t where t.id = :id and t.deleted is null")
                    .setParameter("id", trackID).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}
