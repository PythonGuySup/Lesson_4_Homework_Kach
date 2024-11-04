package ru.itis.users.app;

import ru.itis.users.controllers.UsersController;

/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class Main {
    public static void main(String[] args) {
        UsersController usersController = new UsersController();
        usersController.run();
    }
}
