package eureka.util;

import javax.security.auth.login.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static AnnotationConfiguration configuration;
    private static SessionFactory sessionFactory;


    public static AnnotationConfiguration getConfiguration() {
        getSessionFactory();
        return configuration;
    }

    public synchronized static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            getSessionFactory(new AnnotationConfiguration().configure());
        }
        
        return sessionFactory;
    }

    public synchronized static SessionFactory getSessionFactory(AnnotationConfiguration cfg) {

        if (sessionFactory == null) {
            initSessionFactory(cfg);
        }

        return sessionFactory;
    }

    public static Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public static Transaction beginTransaction() {
        return getCurrentSession().beginTransaction();
    }

    public static void commit() {
        getCurrentSession().getTransaction().commit();
    }

    public static void rollback() {
        getCurrentSession().getTransaction().rollback();
    }

    private static void initSessionFactory(AnnotationConfiguration cfg) {
        try {

            // Create the SessionFactory from hibernate.cfg.xml
            configuration = cfg;

            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
