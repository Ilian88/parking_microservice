package com.imarkov.parking.external;

import com.imarkov.parking.model.dao.Vehicle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentInfoGateAway {
    private static final String GET_PAYMENT_INFO_URL = "http://localhost:8085/payment/info";
    private final RestTemplate restTemplate;

    public PaymentInfoGateAway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentInfoResponse getInfoTillNow(Vehicle vehicle) {
        HttpEntity<GetPaymentInfoDTO> getPaymentInfoDTOHttpEntity = getGetPaymentInfoDTOHttpEntity(vehicle);
        ResponseEntity<PaymentInfoResponse> infoTillNowEntity = restTemplate.exchange(
                GET_PAYMENT_INFO_URL,
                HttpMethod.POST,
                getPaymentInfoDTOHttpEntity,
                PaymentInfoGateAway.PaymentInfoResponse.class);

        return infoTillNowEntity.getBody();
    }

    private static HttpEntity<PaymentInfoGateAway.GetPaymentInfoDTO> getGetPaymentInfoDTOHttpEntity(Vehicle vehicle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders httpHeaders = new HttpHeaders();

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            String token = jwtAuthenticationToken.getToken().getTokenValue();
            httpHeaders.setBearerAuth(token);
        }

        return new HttpEntity<>(
                new GetPaymentInfoDTO(vehicle.getParkingSession().getEnteredAt(), LocalDateTime.now(), 2.0),
                httpHeaders);
    }

    public record GetPaymentInfoDTO(LocalDateTime enteredAt, LocalDateTime leftAt, double ratePerHour){}
    public record PaymentInfoResponse(long timeSpentInHours, BigDecimal amountTillNow){}
}
