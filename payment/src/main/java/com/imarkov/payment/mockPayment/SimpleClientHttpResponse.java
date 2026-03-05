package com.imarkov.payment.mockPayment;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SimpleClientHttpResponse implements ClientHttpResponse {
    private final byte[] body;
    private final HttpStatus status;

    public SimpleClientHttpResponse(String body, HttpStatus status) {
        this.body = body.getBytes(StandardCharsets.UTF_8);
        this.status = status;
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return status;
    }

    @Override
    public String getStatusText() throws IOException {
        return "Successful";
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(body);
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
