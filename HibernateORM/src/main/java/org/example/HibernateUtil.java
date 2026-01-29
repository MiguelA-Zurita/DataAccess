package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HibernateUtil { //Hibernate utility class
    private static SessionFactory factory;

    public static synchronized void buildSessionFactory() {
        if (factory == null) {
            try {
                Configuration cfg = new Configuration().configure();
                applyConfiguration(cfg);
                factory = cfg.buildSessionFactory();
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

    public static void applyConfiguration(Configuration cfg) {
        try (FileInputStream fis = new FileInputStream("src/main/resources/bbdd_connection.txt")) {
            InputStreamReader isr = new InputStreamReader(fis);
            java.util.Properties properties = new java.util.Properties();
            properties.load(isr);
            properties.forEach((k, v) -> {
                System.out.println(k.toString() + " = " + v.toString());
                switch (k.toString()) {
                    case "url":
                        cfg.setProperty("hibernate.connection.url", v.toString());
                        break;
                    case "user":
                        cfg.setProperty("hibernate.connection.username", v.toString());
                        break;
                    case "password":
                        cfg.setProperty("hibernate.connection.password", v.toString());
                        break;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            cfg.setProperty("hibernate.current_session_context_class", "thread");
        }

    }
}
