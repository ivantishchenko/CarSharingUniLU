package lu.tishchenko.jsf.beans.crud.helper;

import lu.tishchenko.ejb.entity.helper.AbstractDBObject;
import lu.tishchenko.ejb.facade.helper.AbstractDBObjectFacade;

import javax.enterprise.context.Conversation;
import java.io.Serializable;

/**
 * Created by Ivan on 04.01.2017.
 */
public abstract class AbstractDBObjectCrudBean<T extends AbstractDBObject> implements Serializable {
    protected T entity;
    private boolean readOnly = true;

    public abstract Class getClazz();

    public abstract Conversation getConversation();

    public abstract AbstractDBObjectFacade getFacade();

    public String startNewEntity() {
        if (getConversation().isTransient()) getConversation().begin();
        try {
            entity = (T) getClazz().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        readOnly = false;
        return null;
    }

    public String startEditEntity(T editEntity) {
        if (getConversation().isTransient()) getConversation().begin();
        this.entity = editEntity;
        readOnly = false;
        return null;
    }

    public String doSaveEdit() {
        entity = (T) getFacade().saveOrUpdate(entity);
        endConversation();
        return null;
    }

    public String startDelete(T toDeleteEntity) {
        if (getConversation().isTransient()) getConversation().begin();
        this.entity = toDeleteEntity;
        readOnly = true;
        return null;
    }

    public String doDelete() {
        getFacade().delete(entity);
        endConversation();
        return null;
    }

    public String doCancel() {
        endConversation();
        return null;
    }

    public void endConversation() {
        readOnly = true;
        if (!getConversation().isTransient()) {
            getConversation().end();
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}