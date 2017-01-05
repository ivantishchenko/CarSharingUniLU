package lu.tishchenko.jsf.beans.crud;

import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.facade.helper.AbstractDBObjectFacade;
import lu.tishchenko.ejb.facade.UserFacade;
import lu.tishchenko.jsf.beans.crud.helper.AbstractDBObjectCrudBean;
import lu.tishchenko.jsf.beans.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Ivan on 04.01.2017.
 */
@Named
@ConversationScoped
public class UserCrudBean extends AbstractDBObjectCrudBean<User> {
    @Inject
    private Conversation conversation;
    @Inject
    private UserFacade facade;
    @Inject
    private SessionData sessionData;



    // If user delete himself - System make him logout
    @Override
    public String doDelete() {
        if (entity.equals(sessionData.getLoggedUser())) {
            sessionData.setLoggedUser(null);
        }
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return User.class;
    }

    @Override
    public Conversation getConversation() {
        return conversation;
    }

    @Override
    public AbstractDBObjectFacade getFacade() {
        return facade;
    }
}
