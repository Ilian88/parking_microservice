package com.markov.identity_provider.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.stereotype.Component;
import java.security.Principal;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import java.util.Collections;

@Component
public class CustomPasswordGrantAuthenticationProvider implements AuthenticationProvider {
    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<?> tokenGenerator;
    private final RegisteredClientRepository registeredClientRepository;

    public CustomPasswordGrantAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                                     OAuth2TokenGenerator<?> tokenGenerator, RegisteredClientRepository registeredClientRepository) {
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomPasswordGrantAuthenticationToken token =
                (CustomPasswordGrantAuthenticationToken) authentication;

        RegisteredClient registeredClient = registeredClientRepository.findByClientId(token.getClientId());
        if (registeredClient == null) throw new OAuth2AuthenticationException("invalid_client");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(token.getPrincipal(),null, Collections.emptyList());

        OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(usernamePasswordAuthenticationToken)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(new AuthorizationGrantType("urn:custom:password")) // добави това
                .authorizedScopes(registeredClient.getScopes())
                .build();

        OAuth2Token generatedToken = tokenGenerator.generate(tokenContext);

        if (generatedToken == null) {
            throw new RuntimeException("Failed to generate token");
        }

        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                generatedToken.getTokenValue(),
                generatedToken.getIssuedAt(),
                generatedToken.getExpiresAt(),
                registeredClient.getScopes()
        );

        OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(token.getUsername())
                .authorizationGrantType(new AuthorizationGrantType("urn:custom:password"))
                .accessToken(accessToken)
                .attribute(Principal.class.getName(), usernamePasswordAuthenticationToken)
                .build();

        authorizationService.save(authorization);

        return new OAuth2AccessTokenAuthenticationToken(registeredClient, usernamePasswordAuthenticationToken, accessToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomPasswordGrantAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
