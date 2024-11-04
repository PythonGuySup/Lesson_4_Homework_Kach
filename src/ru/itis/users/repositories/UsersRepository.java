package ru.itis.users.repositories;

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
    private final Map<String, User> users;

    public UsersRepository() {
        this.users = new HashMap<>();
    }

    public void save(User user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException("user is null");
        }

       this.users.put(user.getUuid(), user);
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
