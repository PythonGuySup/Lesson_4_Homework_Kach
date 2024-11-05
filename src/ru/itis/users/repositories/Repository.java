package ru.itis.users.repositories;

import ru.itis.users.models.User;

import java.util.List;

// CRUD operations
public interface Repository<DataType, IdType> {
    public void save(DataType toSave);
    public void delete(DataType toDelete);
    public DataType findById(IdType id);

    public List<DataType> findAll();

}
