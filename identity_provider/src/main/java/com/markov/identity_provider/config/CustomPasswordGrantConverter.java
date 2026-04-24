package com.markov.identity_provider.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordGrantConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter("grant_type");
        if (!"urn:custom:password".equals(grantType)) return null;

        String username = request.getParameter("username");
        String clientId = request.getParameter("client_id");

        if (username == null || clientId == null) {
            throw new OAuth2AuthenticationException("invalid_request");
        }


        return new CustomPasswordGrantAuthenticationToken(username, clientId);
    }
}
