package com.imarkov.payment.util;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CardEncryptor {
    private static final SecretKey secretKey = AESKeyGenerator.generateKey(32);

    public static String encrypt(String cardNumber) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(cardNumber.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }

}
