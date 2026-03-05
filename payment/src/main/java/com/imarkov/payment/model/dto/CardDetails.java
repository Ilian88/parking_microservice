package com.imarkov.payment.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CardDetails(@NotBlank String cardNumber, @NotBlank String cardHolderName, @NotBlank @Size(min = 4, max = 4) String CCV) {
}
