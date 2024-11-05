package ru.itis.users.services;

import ru.itis.users.exceptions.InternalPasswordGeneratorException;
import ru.itis.users.exceptions.InvalidEmailOrPasswordException;
import ru.itis.users.exceptions.EntityNotFoundException;
import ru.itis.users.exceptions.NotSuchUserWithEmailException;
import ru.itis.users.models.Salt;
import ru.itis.users.models.User;
import ru.itis.users.repositories.UsersRepository;
import ru.itis.users.repositories.SaltRepository;
import ru.itis.users.secutiry.PasswordGenerator;
import ru.itis.users.validators.UserValidator;
import ru.itis.users.validators.ValidatorInterface;

import java.util.Optional;
import java.util.UUID;

/**
 * Lesson_4
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersService {

    private final UsersRepository repository;
    private final SaltRepository saltRepository;
    private final ValidatorInterface<User> validator;
    private final PasswordGenerator passwordGenerator;

    public UsersService() {
        this.repository = new UsersRepository();
        this.saltRepository = new SaltRepository();
        this.validator = new UserValidator();
        this.passwordGenerator = new PasswordGenerator();

        byte[] salt = PasswordGenerator.generateSalt();
        Salt saltDTO = new Salt(UUID.randomUUID().toString(), salt);
        passwordGenerator.setSalt(salt);
        saltRepository.save(saltDTO);
    }

    public void addUser(String email, String password) throws InvalidEmailOrPasswordException, InternalPasswordGeneratorException {
        User user = new User("unsafeUser", email, password);

        if (validator.validate(user)) {
            String hashedPassword = passwordGenerator.generateSaltedPassword(password);
            User hashedUser = new User(UUID.randomUUID().toString(), email, hashedPassword);
            repository.save(hashedUser);
        } else {
            throw new InvalidEmailOrPasswordException("Invalid email or password");
        }

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
