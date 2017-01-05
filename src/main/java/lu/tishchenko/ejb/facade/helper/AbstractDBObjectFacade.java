package lu.tishchenko.ejb.facade.helper;

import lu.tishchenko.ejb.entity.helper.AbstractDBObject;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Ivan on 04.01.2017.
 */
public abstract class AbstractDBObjectFacade implements Serializable {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected Logger log;

    public <T> T find(Class<T> clazz, Long id) {
        log.info("Find " + clazz.getName() + " by id=" + id);
        return em.find(clazz, id);
    }

    public void delete(AbstractDBObject dbObject) {
        log.info(dbObject + "Delete");
        dbObject.setDeleted(new Date());
        dbObject.setName(dbObject.getName() + "_d");
        saveOrUpdate(dbObject);
    }

    public AbstractDBObject saveOrUpdate(AbstractDBObject dbObject) {
        log.info("Save or Update " + dbObject);
        return em.merge(dbObject);
    }
}