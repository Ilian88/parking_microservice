package com.imarkov.parking.service.client;

import com.imarkov.parking.model.StayDetails;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.properties.AppProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ParkingStayHelper {
    private final AppProperties appProperties;

    public ParkingStayHelper(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public StayDetails getStayDetails(Vehicle vehicle) {
        long between = ChronoUnit.HOURS.between(vehicle.getParkingSession().getEnteredAt(), LocalDateTime.now());
        Double price = appProperties.getPricing().getEuroCategory().get(vehicle.getEuroCategory().name());

        return new StayDetails(between,
                BigDecimal.valueOf((between + 1) * price),
                appProperties.getPricing().getCurrency());
    }
}
