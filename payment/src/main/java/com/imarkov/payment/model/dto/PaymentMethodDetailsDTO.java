package com.imarkov.payment.model.dto;

import com.imarkov.payment.enums.PaymentMethodType;
import com.imarkov.payment.util.CardEncryptor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class PaymentMethodDetailsDTO {
    private PaymentMethodType methodType;
    private String maskedCard;
    private String provider;

    public PaymentMethodDetailsDTO(String cardNumber) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.maskedCard = CardEncryptor.encrypt(cardNumber);
    }

    public PaymentMethodType getMethodType() {
        return methodType;
    }

    public PaymentMethodDetailsDTO setMethodType(PaymentMethodType methodType) {
        this.methodType = methodType;
        return this;
    }

    @NotBlank(message = "Card number is mandatory")
    @Size(message = "Last 4 digits from the card are required", min = 4, max = 4)
    public String getMaskedCardNumber() {
        return maskedCard;
    }

    public PaymentMethodDetailsDTO setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCard = maskedCardNumber;
        return this;
    }

    @Pattern(regexp = "^(VISA|MASTERCARD)$", message = "Must be one of these: VISA, MASTERCARD")
    public String getProvider() {
        return provider;
    }

    public PaymentMethodDetailsDTO setProvider(String provider) {
        this.provider = provider;
        return this;
    }
}
