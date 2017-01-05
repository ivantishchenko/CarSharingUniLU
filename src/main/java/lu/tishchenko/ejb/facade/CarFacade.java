package lu.tishchenko.ejb.facade;

import lu.tishchenko.ejb.entity.Car;
import lu.tishchenko.ejb.entity.User;
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
public class CarFacade extends AbstractDBObjectFacade {

    public List<Car> getAll() {
        try {
            return em.createQuery("select c from Car c where c.deleted is null").getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }


    public Car findCarByName(String name) {
        try {
            return (Car) em.createQuery("select c from Car c where c.name = :name and c.deleted is null")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public Car getCar(User user) {
        try {
            return (Car) em.createQuery("select c from Car c where c.deleted is null and c.owner = :owner").setParameter("owner", user).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}