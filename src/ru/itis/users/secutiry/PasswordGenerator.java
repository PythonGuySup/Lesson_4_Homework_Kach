package ru.itis.users.secutiry;

import ru.itis.users.exceptions.InternalPasswordGeneratorException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordGenerator {
    private byte[] salt;

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    // PBKDF2 hashing algorithm
    public String generateSaltedPassword(String rawPassword) throws NullPointerException, InternalPasswordGeneratorException {
        if (salt == null | rawPassword == null) {
            throw new NullPointerException("Password is null");
        }

        try {
            return saltPassword(this.salt, rawPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new InternalPasswordGeneratorException(e.getMessage());
        }
    }

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        return salt;
    }

    private String saltPassword(byte[] salt, String rawPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();

        return Arrays.toString(hashedPassword);
    }




}