package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Bob", (byte) 25, "bob@mail.com");
//        userService.saveUser("Mike", (byte) 33, "mike@mail.com");
//        userService.saveUser("Elena", (byte) 21, "elena@mail.com");
//        userService.saveUser("Sveta", (byte) 19, "sveta@mail.com");
//        userService.getAllUsers();
//        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
