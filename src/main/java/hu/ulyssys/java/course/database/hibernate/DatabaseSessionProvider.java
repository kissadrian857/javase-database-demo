package hu.ulyssys.java.course.database.hibernate;

import hu.ulyssys.java.course.database.hibernate.entity.Aircraft;
import hu.ulyssys.java.course.database.hibernate.entity.Student;
import hu.ulyssys.java.course.database.hibernate.entity.Tank;
import hu.ulyssys.java.course.database.hibernate.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class DatabaseSessionProvider {
    private static DatabaseSessionProvider instance = null;

    private static Session sessionObj;

    private DatabaseSessionProvider() {
        configDatabase();
    }

    private void configDatabase() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Tank.class);
        configuration.addAnnotatedClass(Aircraft.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);
        Properties properties = new Properties();
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "Admin123");
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/demo");
        properties.put("hibernate.show_sql", true);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        sessionObj = configuration.buildSessionFactory(serviceRegistry).openSession();
    }

    public Session getSessionObj() {
        return sessionObj;
    }

    public static DatabaseSessionProvider getInstance() {
        if (instance == null) {
            instance = new DatabaseSessionProvider();
        }
        return instance;
    }
}
