package lu.tishchenko.ejb.entity;

import lu.tishchenko.ejb.entity.helper.AbstractDBObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ivan on 04.01.2017.
 */
@Entity
public class User extends AbstractDBObject {
    private String password;
    private boolean adminRole;
    private Car car;
    private List<Trip> tracks;
    private List<Trip> reservedTracks;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "companions", fetch=FetchType.EAGER)
    public List<Trip> getReservedTracks() {
        return reservedTracks;
    }

    public void setReservedTracks(List<Trip> reservedTracks) {
        this.reservedTracks = reservedTracks;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public List<Trip> getTracks() {
        return tracks;
    }

    public void setTracks(List<Trip> tracks) {
        this.tracks = tracks;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "adminRole")
    public boolean isAdminRole() {
        return adminRole;
    }

    public void setAdminRole(boolean adminRole) {
        this.adminRole = adminRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (adminRole != user.adminRole) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (adminRole ? 1 : 0);
        return result;
    }
}
