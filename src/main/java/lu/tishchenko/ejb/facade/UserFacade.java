package lu.tishchenko.ejb.facade;

import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.entity.helper.AbstractDBObjectFacade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Ivan on 04.01.2017.
 */
@Named
@Stateless
public class UserFacade extends AbstractDBObjectFacade {
    public List<User> getAll() {
        try {
            return em.createQuery("select u from User u where u.deleted is null").getResultList();
        } catch (NoResultException nrEx) {
            return null;
        }
    }

    public User findUserByUsername(String username) {
        try {
            return (User) em.createQuery("select u from User u where u.name = :username and u.deleted is null")
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException nrEx) {
            return null;
        }
    }
}