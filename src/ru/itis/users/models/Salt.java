package ru.itis.users.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Salt {

    private final String uuid;

    private final byte[] salt;

    public Salt(String uuid, byte[] salt) {
        this.uuid = uuid;
        this.salt = salt;
    }

    public String getUuid() { return uuid; }

    public byte[] getSalt() { return salt; }

    @Override
    public String toString() { return "Salt " + uuid + ": " + Arrays.toString(salt); }
}
