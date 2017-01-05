package lu.tishchenko.jsf.beans.model;

import lu.tishchenko.ejb.entity.Trip;

import javax.enterprise.inject.Model;

/**
 * Created by Ivan on 05.01.2017.
 */

public class TripModel {
    private String name;
    private String owner;
    private String startLocation;
    private String stopLocation;
    private Integer maxCompanions;
    private Integer freePlaces;

    public TripModel(Trip trip) {
        this.name = trip.getName();
        this.owner = trip.getOwner().getName();
        this.startLocation = trip.getStartLocation();
        this.stopLocation = trip.getStopLocation();
        this.maxCompanions = trip.getMaxCompanions();
        this.freePlaces = trip.getFreePlaces();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    public Integer getMaxCompanions() {
        return maxCompanions;
    }

    public void setMaxCompanions(Integer maxCompanions) {
        this.maxCompanions = maxCompanions;
    }

    public Integer getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(Integer freePlaces) {
        this.freePlaces = freePlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripModel trackInfo = (TripModel) o;

        if (name != null ? !name.equals(trackInfo.name) : trackInfo.name != null) return false;
        if (owner != null ? !owner.equals(trackInfo.owner) : trackInfo.owner != null) return false;
        if (startLocation != null ? !startLocation.equals(trackInfo.startLocation) : trackInfo.startLocation != null)
            return false;
        if (stopLocation != null ? !stopLocation.equals(trackInfo.stopLocation) : trackInfo.stopLocation != null)
            return false;
        if (maxCompanions != null ? !maxCompanions.equals(trackInfo.maxCompanions) : trackInfo.maxCompanions != null)
            return false;
        return freePlaces != null ? freePlaces.equals(trackInfo.freePlaces) : trackInfo.freePlaces == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (startLocation != null ? startLocation.hashCode() : 0);
        result = 31 * result + (stopLocation != null ? stopLocation.hashCode() : 0);
        result = 31 * result + (maxCompanions != null ? maxCompanions.hashCode() : 0);
        result = 31 * result + (freePlaces != null ? freePlaces.hashCode() : 0);
        return result;
    }
}