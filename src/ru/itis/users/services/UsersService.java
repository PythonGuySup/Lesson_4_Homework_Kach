package ru.itis.users.services;

import ru.itis.users.exceptions.InvalidEmailOrPasswordException;
import ru.itis.users.exceptions.EntityNotFoundException;
import ru.itis.users.exceptions.NotSuchUserWithEmailException;
import ru.itis.users.models.User;
import ru.itis.users.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersService {

    private final UsersRepository repository;

    public UsersService() {
        this.repository = new UsersRepository();
    }

    public void addUser(String email, String password) throws InvalidEmailOrPasswordException {
        User user = new User(UUID.randomUUID().toString(), email, password);

        repository.save(user);
    }

    public void delUser(String email) throws InvalidEmailOrPasswordException, EntityNotFoundException, NotSuchUserWithEmailException {
        Optional<User> user = repository.findUserByEmail(email);

        if (user.isPresent()) {
            repository.delete(user.get());
        } else {
            throw new NotSuchUserWithEmailException("User with email " + email + " not found");
        }

    }

    public User[] getUsers() {
        return repository.getUsers();
    }
}
