package com.imarkov.parking.model.dto;

public record RegisterDTO(String username, String password, String accountType,
        String email, String phone, String companyName) {
}