package ru.itis.users.validators;

import ru.itis.users.models.User;

public class UserValidator implements ValidatorInterface<User> {
    @Override
    public boolean validate(User toValidate) throws NullPointerException {
        if (toValidate == null) {
            throw new NullPointerException("User is null");
        }
        String email = toValidate.getEmail();
        String password = toValidate.getPassword();

        if (email == null || password == null) {
            return false;
        }

        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$") && password.matches("^[a-zA-Z0-9]+$");
    }
}
