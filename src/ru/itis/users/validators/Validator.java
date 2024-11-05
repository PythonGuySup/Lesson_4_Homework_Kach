package ru.itis.users.validators;

public interface Validator<T> {
    boolean validate(T toValidate) throws NullPointerException;
}
