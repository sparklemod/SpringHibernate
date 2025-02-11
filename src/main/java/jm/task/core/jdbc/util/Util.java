package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.LoggerService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final LoggerService logger = new LoggerService(Util.class);

    private static final String URL = "jdbc:mysql://localhost:44444/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final SessionFactory sessionFactory;

    private Util() {
    }

    static {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            logger.logError("Ошибка создания SessionFactory: " + e.getMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}