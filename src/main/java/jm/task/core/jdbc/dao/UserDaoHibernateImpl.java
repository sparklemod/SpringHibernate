package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.LoggerService;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final LoggerService logger = new LoggerService(UserDaoHibernateImpl.class);
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.logError("Ошибка при создании таблицы", e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.logError("Ошибка при удалении таблицы", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            logger.logInfo("Пользователь с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            logger.logError("Ошибка при сохранении записи", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.logError("Ошибка при удалении записи", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            logger.logError("Ошибка при выборке", e);
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.logError("Ошибка при очистке таблицы", e);
        }
    }
}
