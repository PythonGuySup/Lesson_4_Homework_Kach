package ru.itis.users.validators;

public interface Validator<T> {
    public boolean validate(T toValidate) throws NullPointerException;
}
