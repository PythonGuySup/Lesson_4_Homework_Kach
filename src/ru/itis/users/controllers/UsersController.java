package ru.itis.users.controllers;

import ru.itis.users.exceptions.InvalidEmailOrPasswordException;
import ru.itis.users.exceptions.EntityNotFoundException;
import ru.itis.users.exceptions.NotSuchUserWithEmailException;
import ru.itis.users.models.User;
import ru.itis.users.services.UsersService;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersController {

    private final Scanner scanner;

    private final UsersService userService;


    public UsersController() {
        this.scanner = new Scanner(System.in);
        this.userService = new UsersService();
    }

    public void run() {
        try {
            while (true) {
                printMainMenu();
                processCommandFromMainMenu();
            }
        } catch (Exception e) {
            System.out.println("Внутрення ошибка");
            System.out.println("Вывести трассу ошибки? Y/n");
            scanner.nextLine();
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("y")) {
                e.printStackTrace();
            } else if (command.equals("n")) {
                System.out.println("Аварийная остановка сервиса...");
                System.exit(1);
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Добрый день!");
        System.out.println("Выберите необходимый пункт меню:");
        System.out.println("1. Добавить пользователя");
        System.out.println("2. Удалить пользователя (по email)");
        System.out.println("3. Показать список пользователей");
        System.out.println("0. Выход");
    }

    private void processCommandFromMainMenu() {
        int command = scanner.nextInt();

        switch (command) {
            case 1 -> handleAddUser();
            case 2 -> handleDelUser(); // by email
            case 3 -> handleShowUsers();
            case 0 -> System.exit(0);
        }
    }


    private void handleAddUser() {

        System.out.println("Введите email в формате имя@ваш_провайдер.домен_верх_ур\nПароль может содержать только латинские буквы и цифры и в конце $");
        System.out.println("Введите email пользователя:");
        scanner.nextLine();
        String email = scanner.nextLine();
        System.out.println("Введите пароль пользователя:");
        String password = scanner.nextLine();

        try {
            userService.addUser(email, password);
            System.out.println("Пользователь успешно добавлен!");
        } catch (InvalidEmailOrPasswordException e) {
            System.out.println("Неправильный формат пароля или email. Пожалуйста, повторите ввод снова");
        } catch (Exception e) {
            System.out.println("Внутрення ошибка. Пожалуйста, свяжитесь с админинстратором");
        }

    }

    private void handleDelUser() {
        System.out.println("Введите email пользователя, которого нужно удалить");
        scanner.nextLine();
        String email = scanner.nextLine();

        try {
            userService.delUser(email);
            System.out.println("Пользователь успешно удален!");
        } catch (InvalidEmailOrPasswordException e) {
            System.out.println("Неправильный формат пароля или email. Пожалуйста, повторите ввод снова");
        } catch (NotSuchUserWithEmailException e) {
            System.out.println("Пользователя с таким email не найден в репозитории");
        } catch (EntityNotFoundException e) {
            System.out.println("Внутрення ошибка сервера");
        }

    }

    private void handleShowUsers() {
        User[] users = userService.getUsers();

        if (users.length == 0) {
            System.out.println("Нет пользователей");
        }

        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
