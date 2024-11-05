package ru.itis.users.validators;

import ru.itis.users.models.User;

public class UserValidator implements Validator<User> {
    @Override
    public boolean validate(User user) throws NullPointerException {
        if (user == null) {
            throw new NullPointerException("User is null");
        }
        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || password == null) {
            return false;
        }

        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$") && password.matches("^[a-zA-Z0-9]+$");
    }
}
