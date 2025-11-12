package org.aston.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Dotenv dotenv = Dotenv.load();

            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            cfg.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
            cfg.setProperty("hibernate.connection.username", dotenv.get("DB_USER"));
            cfg.setProperty("hibernate.connection.password", dotenv.get("DB_PASS"));

            return cfg.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Hibernate initialization error: " + e.getMessage(), e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
