package lu.tishchenko.webservice.rest;

import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.jsf.beans.action.TripAction;
import lu.tishchenko.jsf.beans.model.SessionData;
import lu.tishchenko.jsf.beans.model.TripModel;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ivan on 05.01.2017.
 */
@Path("/trips")
public class SharingRestService {
    @Inject
    private SessionData sessionData;
    @Inject
    private TripAction tripAction;


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String webServiceInfo() {
        return "The web service for the car sharing app";
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TripModel> searchTrips(@QueryParam("start") String start, @QueryParam("destination") String stop) {

        // access control fail
        if (!sessionData.isLoggedIn()) {
            return null;
        }

        List<Trip> myTrips = tripAction.getTrackByDestination(start, stop);

        List<TripModel> responseTrips = new ArrayList<TripModel>();


        for (Trip trip : myTrips) {
            responseTrips.add(new TripModel(trip));
        }

        return responseTrips;
    }

    @GET
    @Path("/book")
    @Produces(MediaType.TEXT_PLAIN)
    public String getReservation(@QueryParam("trip") String trip) {

        if (!sessionData.isLoggedIn()) return "Access control failed";
        if (tripAction.reserv(sessionData.getLoggedUser(), trip)) return "Signed up for a trip";
        else return "Already on a trip. Did not book the trip";

    }
}
