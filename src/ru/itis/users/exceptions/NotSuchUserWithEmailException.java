package ru.itis.users.exceptions;

public class NotSuchUserWithEmailException extends RuntimeException{
    public NotSuchUserWithEmailException(String message){
        super(message);
    }
}
