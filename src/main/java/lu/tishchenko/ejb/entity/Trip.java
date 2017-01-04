package lu.tishchenko.ejb.entity;

import lu.tishchenko.ejb.entity.helper.AbstractDBObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 04.01.2017.
 */
@Entity
public class Trip extends AbstractDBObject {
    private User owner;
    private String startLocation;
    private String stopLocation;
    private Integer maxCompanions;
    private Integer freePlaces;
    private List<User> companions;

    @Column(name = "freePlaces")
    public Integer getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(Integer freePlaces) {
        this.freePlaces = freePlaces;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "startLocation")
    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    @Column(name = "stopLocation")
    public String getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(String stopLocation) {
        this.stopLocation = stopLocation;
    }

    @Column(name = "maxCompanions")
    public Integer getMaxCompanions() {
        return maxCompanions;
    }

    public void setMaxCompanions(Integer maxCompanions) {
        this.maxCompanions = maxCompanions;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<User> getCompanions() {
        return companions;
    }

    public void setCompanions(List<User> companions) {
        this.companions = companions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trip track = (Trip) o;

        if (startLocation != null ? !startLocation.equals(track.startLocation) : track.startLocation != null)
            return false;
        if (stopLocation != null ? !stopLocation.equals(track.stopLocation) : track.stopLocation != null) return false;
        if (maxCompanions != null ? !maxCompanions.equals(track.maxCompanions) : track.maxCompanions != null)
            return false;
        return freePlaces != null ? freePlaces.equals(track.freePlaces) : track.freePlaces == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (startLocation != null ? startLocation.hashCode() : 0);
        result = 31 * result + (stopLocation != null ? stopLocation.hashCode() : 0);
        result = 31 * result + (maxCompanions != null ? maxCompanions.hashCode() : 0);
        result = 31 * result + (freePlaces != null ? freePlaces.hashCode() : 0);
        return result;
    }
}