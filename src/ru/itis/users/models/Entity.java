package ru.itis.users.models;

public interface Entity<IdType>  {
    IdType getId();
    @Override
    String toString();
}
