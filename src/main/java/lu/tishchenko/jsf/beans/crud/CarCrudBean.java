package lu.tishchenko.jsf.beans.crud;

import lu.tishchenko.ejb.entity.Car;
import lu.tishchenko.ejb.entity.Trip;
import lu.tishchenko.ejb.entity.User;
import lu.tishchenko.ejb.facade.CarFacade;
import lu.tishchenko.ejb.facade.UserFacade;
import lu.tishchenko.ejb.facade.helper.AbstractDBObjectFacade;
import lu.tishchenko.jsf.beans.crud.helper.AbstractDBObjectCrudBean;
import lu.tishchenko.jsf.beans.model.SessionData;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Ivan on 05.01.2017.
 */
@Named
@ConversationScoped
public class CarCrudBean extends AbstractDBObjectCrudBean<Car> {
    @Inject
    private Conversation conversation;
    @Inject
    private CarFacade facade;
    @Inject
    private SessionData sessionData;

    @Override
    public String doSaveEdit() {
        entity.setOwner(sessionData.getLoggedUser());
        sessionData.getLoggedUser().setCar(entity);
        return super.doSaveEdit();
    }

    @Override
    public String doDelete() {
        sessionData.getLoggedUser().setCar(null);
        return super.doDelete();
    }

    @Override
    public Class getClazz() {
        return Car.class;
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