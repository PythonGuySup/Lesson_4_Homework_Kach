package ru.itis.users.repositories;

import ru.itis.users.models.Salt;

import java.util.HashMap;
import java.util.Map;

public class SaltRepository {
    private final Map<String, Salt> salts;

    public SaltRepository() { this.salts = new HashMap<>(); }

    public void save(Salt salt) throws NullPointerException {
        if (salt == null) {
            throw new NullPointerException("salt is null");
        }

        this.salts.put(salt.getUuid(), salt);
    }

    public Salt[] getSalts() { return this.salts.values().toArray(new Salt[0]); }
}
