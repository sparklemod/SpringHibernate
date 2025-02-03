package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Анастасия", "Александрова", (byte) 23);
        userService.saveUser("Николай", "Алексеевич", (byte) 50);
        userService.saveUser("Мария", "Петрова", (byte) 28);
        userService.saveUser("Иван", "Иванов", (byte) 39);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
