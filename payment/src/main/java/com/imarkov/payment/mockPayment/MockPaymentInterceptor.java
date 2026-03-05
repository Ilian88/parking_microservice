package com.imarkov.payment.mockPayment;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class MockPaymentInterceptor implements ClientHttpRequestInterceptor {
    private final SimpleClientHttpResponse simpleClientHttpResponse;

    public MockPaymentInterceptor(SimpleClientHttpResponse simpleClientHttpResponse) {
        this.simpleClientHttpResponse = simpleClientHttpResponse;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (request.getURI().getPath().contains("/pay")) {
            return simpleClientHttpResponse;
        } else {
            throw new RuntimeException("Endpoint is not supported");
        }
    }
}
