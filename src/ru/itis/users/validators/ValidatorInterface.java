package ru.itis.users.validators;

public interface ValidatorInterface<T> {
    public boolean validate(T toValidate) throws NullPointerException;
}
