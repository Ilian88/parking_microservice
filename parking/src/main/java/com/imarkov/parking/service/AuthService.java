package com.imarkov.parking.service;

import com.imarkov.parking.model.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.imarkov.parking.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.imarkov.parking.model.dao.user.UserEntity;

public class AuthService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${identity-provider.token-uri}")
    private String tokenUri;

    @Value("${identity-provider.client-id}")
    private String clientId;

    @Value("${identity-provider.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponse login(String username, String password) {
        // 1. Валидирай user локално
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getEncPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // 2. Вземи токен от IDP — само username, без парола
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "urn:custom:password");
        body.add("username", username);
        body.add("client_id", clientId);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(tokenUri, entity, TokenResponse.class);
    }
}
