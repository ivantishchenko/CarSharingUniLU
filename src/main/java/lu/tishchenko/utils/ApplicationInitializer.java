package lu.tishchenko.utils;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ivan on 04.01.2017.
 */
@Singleton
@Startup
public class ApplicationInitializer {
    @Inject
    private Logger log;
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        log.info("init app....");
    }
}