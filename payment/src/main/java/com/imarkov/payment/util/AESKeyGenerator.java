package com.imarkov.payment.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESKeyGenerator {
    private static final KeyGenerator keyGenerator;

    static {
        try {
           keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static SecretKey generateKey(int bytes) {
        keyGenerator.init(bytes);
        return keyGenerator.generateKey();
    }
}
