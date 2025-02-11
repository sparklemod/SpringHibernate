package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;

public class Main {
    private static final LoggerService logger = new LoggerService(UserDaoHibernateImpl.class);

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Анастасия", "Александрова", (byte) 23);
        userService.saveUser("Николай", "Алексеевич", (byte) 50);
        userService.saveUser("Мария", "Петрова", (byte) 28);
        userService.saveUser("Иван", "Иванов", (byte) 39);

        for (User user : userService.getAllUsers()) {
            logger.logInfo(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
