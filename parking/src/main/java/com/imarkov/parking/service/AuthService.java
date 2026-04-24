package com.imarkov.parking.service;

import com.imarkov.parking.exception.AuthException;
import com.imarkov.parking.model.TokenResponse;
import com.imarkov.parking.model.dto.LoginDTO;
import com.imarkov.parking.model.dto.RegisterDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.imarkov.parking.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.imarkov.parking.model.dao.user.UserEntity;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${identity-provider.token-uri}")
    private String tokenUri;

    @Value("${identity-provider.client-id}")
    private String clientId;

    @Value("${identity-provider.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;

    public AuthService(UserRepo userRepository, PasswordEncoder passwordEncoder, RestTemplate restTemplate, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    public TokenResponse login(LoginDTO loginDTO) {
        // 1. Валидирай user локално
        UserEntity user = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // 2. Вземи токен от IDP — само username, без парола
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "urn:custom:password");
        body.add("username", loginDTO.username());
        body.add("client_id", clientId);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        log.debug("Fetching for token from IDP for user {}", loginDTO.username());

        return restTemplate.postForObject(tokenUri, entity, TokenResponse.class);
    }

    public void register(RegisterDTO registerDTO) {
        UserEntity userEntity = modelMapper.map(registerDTO, UserEntity.class);

        log.info("Registering user {}", registerDTO.username());
        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException | IllegalArgumentException exception) {
            throw new AuthException("There was an error while registering user");
        }
    }
}
