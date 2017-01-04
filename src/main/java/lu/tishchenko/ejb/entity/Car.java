package lu.tishchenko.ejb.entity;

import lu.tishchenko.ejb.entity.helper.AbstractDBObject;

import javax.persistence.*;

/**
 * Created by Ivan on 04.01.2017.
 */
@Entity
public class Car extends AbstractDBObject {
    private User owner;
    private String info;

    @OneToOne(fetch= FetchType.EAGER)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        return info != null ? info.equals(car.info) : car.info == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}