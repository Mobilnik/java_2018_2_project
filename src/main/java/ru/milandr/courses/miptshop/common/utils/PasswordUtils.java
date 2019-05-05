package ru.milandr.courses.miptshop.common.utils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Formatter;

/**
 * Класс содержит методы для работы с паролями
 */
public class PasswordUtils {

    /**
     * @return new salt as hex string
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static String getNewSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return saltToString(salt);
    }

    /**
     * Transforms byte array into hex salt string
     *
     * @param salt byte array
     * @return hex string
     */
    private static String saltToString(byte[] salt) {
        if (salt == null || salt.length == 0)
            return null;

        Formatter formatter = new Formatter();
        for (byte b : salt) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String getPasswordHash(String sourcePassword, String hexSaltString) throws NoSuchAlgorithmException {
        if (sourcePassword == null)
            return null;

        byte[] salt = stringToSalt(hexSaltString);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(salt);
        byte[] b = messageDigest.digest(sourcePassword.getBytes());
        Formatter formatter = new Formatter();
        for (byte b1 : b) {
            formatter.format("%02x", b1);
        }
        String output = formatter.toString();
        formatter.close();
        return output;
    }

    /**
     * Transforms hex salt string into byte array
     *
     * @param saltString hex-string
     * @return byte array
     */
    private static byte[] stringToSalt(String saltString) {
        if (saltString == null || saltString.length() == 0)
            return new byte[0];

        HexBinaryAdapter adapter = new HexBinaryAdapter();
        return adapter.unmarshal(saltString);
    }
}