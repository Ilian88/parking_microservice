package com.markov.identity_provider.config;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;


public class CustomPasswordGrantAuthenticationToken extends AbstractAuthenticationToken {
    private final String username;
    private final String clientId;

    public CustomPasswordGrantAuthenticationToken(String username, String clientId) {
        super(new ArrayList<>());
        this.username = username;
        this.clientId = clientId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public String getClientId() {
        return clientId;
    }
}
