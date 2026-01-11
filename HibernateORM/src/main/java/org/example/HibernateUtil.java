package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory factory;

    public static synchronized void buildSessionFactory() {
        if (factory == null) {
            try{
            Configuration cfg = new Configuration().configure();
            cfg.setProperty("hibernate.current_session_context_class", "thread");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
            factory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void openSessionAndBindToThread() {
        Session session = factory.openSession();
        ThreadLocalSessionContext.bind(session);
    }


    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            buildSessionFactory();
        }
        return factory;
    }

    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(factory);
        if (session != null) {
            session.close();
        }
    }

    public static void closeSessionFactory() {
        if ((factory != null) && (!factory.isClosed())) {
            factory.close();
        }
    }
}
