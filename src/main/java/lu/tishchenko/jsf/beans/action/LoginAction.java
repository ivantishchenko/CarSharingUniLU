package lu.tishchenko.jsf.beans.action;

import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.facade.UserFacade;
import lu.tishchenko.jsf.beans.model.LoginModel;
import lu.tishchenko.jsf.beans.model.SessionData;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by Ivan on 04.01.2017.
 */
@Model
public class LoginAction {
    @Inject
    private Logger logger;
    @Inject
    private UserFacade userFacade;
    @Inject
    private LoginModel credentials;
    @Inject
    private SessionData sessionData;

    public String update() {
        User currentUser = sessionData.getLoggedUser();
        logger.info("update... (user: " + currentUser.getName() + " / " + currentUser.getPassword() + ")");
        logger.info("newName = " + credentials.getUsername() + " / newPassword = " + credentials.getPassword());

        currentUser.setPassword(credentials.getPassword());
        currentUser.setName(credentials.getUsername());
        userFacade.saveOrUpdate(currentUser);

        // log out after successful update
        sessionData.setLoggedUser(null);
        return "/login.xhtml?faces-redirect=true";
    }

    public String login() {
        logger.info("login... (user: " + credentials.getUsername() + " / " + credentials.getPassword() + ")");
        sessionData.setLoggedUser(null);
        User dbUser = userFacade.findUserByUsername(credentials.getUsername());
        logger.info("dbUser: " + dbUser);
        if (dbUser != null) {
            if (dbUser.getPassword().equals(credentials.getPassword())) {
                sessionData.setLoggedUser(dbUser);
                return "/home.xhtml?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect username or password"));
        return null;
    }

    public String logout() {
        sessionData.setLoggedUser(null);
        return "/login.xhtml?faces-redirect=true";
    }
}