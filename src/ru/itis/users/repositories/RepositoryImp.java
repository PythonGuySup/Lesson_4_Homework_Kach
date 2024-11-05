package ru.itis.users.repositories;

import ru.itis.users.exceptions.EntityNotFoundException;
import ru.itis.users.models.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IdType should be hashable
 * <p>
 * DataType extends Entity<IdType> : make sure that Data support IdType getId(); method
 **/

public abstract class RepositoryImp<DataType extends Entity<IdType>, IdType> implements Repository<DataType, IdType> {
    protected final Map<IdType, DataType> database;

    public RepositoryImp() {
        this.database = new HashMap<>();
    }

    @Override
    public void save(DataType toSave) throws NullPointerException {
        if (toSave == null) {
            throw new NullPointerException("toSave is null");
        }

        this.database.put(toSave.getId(), toSave);
    }

    @Override
    public void delete(DataType toDelete) throws NullPointerException {
        if (toDelete == null) {
            throw new NullPointerException("toDelete is null");
        }

        DataType result = this.database.remove(toDelete.getId());
        if (result == null) {
            throw new EntityNotFoundException("Operation of Delete aborted. toDelete is not found");
        }
    }

    @Override
    public DataType findById(IdType id) {
        return database.get(id);
    }

    @Override
    public List<DataType> findAll() {
        return this.database.values().stream().toList();
    }

}
