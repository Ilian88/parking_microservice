package com.imarkov.payment.beans;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.imarkov.payment.mockPayment.MockPaymentInterceptor;
import com.imarkov.payment.mockPayment.SimpleClientHttpResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class AppBeansConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new MockPaymentInterceptor(
                        new SimpleClientHttpResponse("\"" + UUID.randomUUID() + "\"", HttpStatus.ACCEPTED
                        )
                )
        );

        return restTemplate;
    }
}
