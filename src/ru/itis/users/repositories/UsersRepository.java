package ru.itis.users.repositories;

import ru.itis.users.validators.UserValidator;
import ru.itis.users.exceptions.InvalidEmailOrPasswordException;
import ru.itis.users.exceptions.EntityNotFoundException;
import ru.itis.users.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepository {
    private static final int MAX_USERS_COUNT = 10;
    private static final UserValidator userValidator = new UserValidator();
    private final Map<String, User> users;

    public UsersRepository() {
        this.users = new HashMap<>();
    }

    public void save(User user) throws NullPointerException, InvalidEmailOrPasswordException {
        if (user == null) {
            throw new NullPointerException();
        }

        if (userValidator.validate(user)) {
            this.users.put(user.getUuid(), user); // practically unlimited pairs (2^31 - 1)
        } else {
            throw new InvalidEmailOrPasswordException("Invalid Email or Password");
        }
    }

    public void delete(User user) throws EntityNotFoundException {
        if (user == null) {
            throw new NullPointerException();
        }

        User result = this.users.remove(user.getUuid());
        if (result == null) {
            throw new EntityNotFoundException("Operation of Delete aborted. User not found");
        }
    }

    public Optional<User> findUserByEmail(String email) {
        for (User user : this.users.values()) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public User[] getUsers() {
        return this.users.values().toArray(new User[0]);
    }
}
