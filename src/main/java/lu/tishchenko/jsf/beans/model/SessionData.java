package lu.tishchenko.jsf.beans.model;

import lu.tishchenko.ejb.entity.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Ivan on 04.01.2017.
 */
@SessionScoped
@Named
public class SessionData implements Serializable {
    private User loggedUser;

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}