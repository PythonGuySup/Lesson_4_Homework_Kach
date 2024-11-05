package ru.itis.users.repositories;

import ru.itis.users.models.Salt;
import ru.itis.users.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepository extends RepositoryImp<User, String> {

    public UsersRepository() { super(); }

    public Optional<User> findUserByEmail(String email) {
        for (User user : super.findAll()) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public User[] getUsers() {
        return super.findAll().toArray(new User[0]);
    }
}
