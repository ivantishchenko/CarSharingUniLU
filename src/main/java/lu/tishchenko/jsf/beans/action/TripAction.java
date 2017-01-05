package lu.tishchenko.jsf.beans.action;

import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.facade.TripFacade;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ivan on 05.01.2017.
 */
@Model
public class TripAction implements Serializable{
    @Inject
    private Logger logger;
    @Inject
    private TripFacade trackFacade;


    public List<Trip> getTrackByDestination(String start, String stop) {
        return trackFacade.getTrackByDestination(start, stop);
    }

    public boolean isOwner (User loggedUser, Trip track) {
        return track.getOwner().equals(loggedUser);
    }

    public boolean isReserved(User loggedUser, Trip track) {
        return track.getCompanions().contains(loggedUser);
    }

    public boolean unReserv(User loggedUser, Trip track) {
        if (track == null || loggedUser == null
                || track.getOwner().equals(loggedUser)
                || !track.getCompanions().contains(loggedUser)) {
            return false;
        }
        track.setFreePlaces(track.getFreePlaces() + 1);
        List<User> companions = track.getCompanions();
        companions.remove(loggedUser);
        track.setCompanions(companions);
        List<Trip> tracks = loggedUser.getReservedTracks();
        tracks.remove(track);
        loggedUser.setReservedTracks(tracks);
        trackFacade.saveOrUpdate(track);
        return true;
    }

    public boolean reserv(User loggedUser, Trip track) {
        if (track == null || loggedUser == null
                || track.getOwner().equals(loggedUser)
                || track.getCompanions().contains(loggedUser)
                || track.getFreePlaces() == 0) {
            return false;
        }
        track.setFreePlaces(track.getFreePlaces() - 1);
        List<User> companions = track.getCompanions();
        companions.add(loggedUser);
        track.setCompanions(companions);
        List<Trip> tracks = loggedUser.getReservedTracks();
        tracks.add(track);
        loggedUser.setReservedTracks(tracks);
        trackFacade.saveOrUpdate(track);
        return true;
    }

    public boolean reserv(User loggedUser, String trackName) {
        Trip track = trackFacade.getTrackByName(trackName);
        return reserv(loggedUser, track);
    }
}