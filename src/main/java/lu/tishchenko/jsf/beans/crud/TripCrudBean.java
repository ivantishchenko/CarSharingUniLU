package lu.tishchenko.jsf.beans.crud;

import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.facade.TripFacade;
import lu.tishchenko.ejb.facade.helper.AbstractDBObjectFacade;
import lu.tishchenko.jsf.beans.action.TripAction;
import lu.tishchenko.jsf.beans.crud.helper.AbstractDBObjectCrudBean;
import lu.tishchenko.jsf.beans.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Iterator;

/**
 * Created by Ivan on 05.01.2017.
 */
@Named
@ConversationScoped
public class TripCrudBean extends AbstractDBObjectCrudBean<Trip> {
    @Inject
    private Conversation conversation;
    @Inject
    private TripAction trackAction;
    @Inject
    private TripFacade facade;
    @Inject
    private SessionData sessionData;

    @Override
    public String doSaveEdit() {
        if (entity.getOwner() == null) {
            entity.setOwner(sessionData.getLoggedUser());
            entity.setFreePlaces(entity.getMaxCompanions());
        }
        return super.doSaveEdit();
    }

    // If track delete himself - System make him logout
    @Override
    public String doDelete() {
        Iterator<User> it = entity.getCompanions().iterator();
        while (it.hasNext()) {
            User user = it.next();
            user.getReservedTracks().remove(entity);
        }
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return Trip.class;
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