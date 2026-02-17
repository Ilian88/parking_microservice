package com.imarkov.parking.remote;

import com.imarkov.parking.custom.ThreadExecPool;
import com.imarkov.parking.model.dto.VehicleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class ParkingHistoryGatewayImpl implements ParkingHistoryGateway {
    private final VehicleDTO vehicleDTO;
    private final String parkingHistoryUrl;

    public ParkingHistoryGatewayImpl(VehicleDTO vehicleDTO, String parkingHistoryUrl) {
        Objects.requireNonNull(vehicleDTO, "vehicle is mandatory");
        Objects.requireNonNull(parkingHistoryUrl, "parking history url is mandatory");
        this.vehicleDTO = vehicleDTO;
        this.parkingHistoryUrl = parkingHistoryUrl;
    }

    @Override
    public void sendVehicle() {
        ThreadExecPool.getInstance()
                .submit(new CreateVehicleTask(new RestTemplate()));
    }

    private class CreateVehicleTask implements Runnable {
        private final RestTemplate restTemplate;

        private CreateVehicleTask(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @Override
        public void run() {
            ResponseEntity<Long> responseEntity = restTemplate.postForEntity(parkingHistoryUrl, vehicleDTO, Long.class);
            if (responseEntity.getStatusCode() != HttpStatus.CREATED || responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("ERRORRRRR calling paring history");    // handle properly
            }
        }
    }
}
