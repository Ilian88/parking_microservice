package com.imarkov.parking.service;

import com.imarkov.parking.exception.NoSuchVehicleException;
import com.imarkov.parking.model.CurrencyEnum;
import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dto.*;
import com.imarkov.parking.repo.VehicleRepo;
import com.imarkov.parking.service.client.VehicleService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private static final String GET_PAYMENT_INFO_URL = "http://localhost:8081/payment/info";

    private final VehicleRepo vehicleRepo;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.vehicleRepo = vehicleRepo;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<VehicleGeneralDTO> getAllCurrent() {
        List<Vehicle> all = vehicleRepo.findAllThatInParking().orElse(new ArrayList<>());
        return all.stream()
                .map(VehicleMapper::mapGeneralVehicleDTO)
                .toList();
    }

    @Override
    public VehicleCreatedDTO createVehicle(VehicleEnterDTO vehicleEnterDTO) {
        Vehicle vehicle;
        if (vehicleEnterDTO instanceof CarEnterDTO carEnterDTO) {
            vehicle = VehicleDTOMapper.mapCreateCarDtoToEntity(carEnterDTO);
        } else if (vehicleEnterDTO instanceof TruckEnterDTO truckEnterDTO) {
            vehicle = VehicleDTOMapper.mapCreateTruckDtoToEntity(truckEnterDTO);
        } else {
            throw new NoSuchVehicleException("There vehicle types can be either CAR or TRUCK");
        }

        Vehicle save = vehicleRepo.save(vehicle);

        VehicleCreatedDTO vehicleCreatedDTO = new VehicleCreatedDTO();
        vehicleCreatedDTO.setLicensePlate(save.getLicensePlate())
                .setEnteredAt(save.getParkingSession().getEnteredAt());

        logger.info("Car is created {}", save);
        return vehicleCreatedDTO;
    }

    @Override
    public PaymentInfoDTO getPaymentInfo(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate)
                .orElseThrow(()-> new NoSuchVehicleException("There is no such vehicle with plate: "  + licensePlate));

        HttpEntity<GetPaymentInfoDTO> httpEntity = getGetPaymentInfoDTOHttpEntity(vehicle);
        ResponseEntity<PaymentInfoResponse> infoTillNowEntity = restTemplate.exchange(
                    GET_PAYMENT_INFO_URL,
                    HttpMethod.POST,
                    httpEntity,
                    PaymentInfoResponse.class);

        PaymentInfoResponse infoTillNow = infoTillNowEntity.getBody();
        if (infoTillNow == null) {
            throw new RuntimeException("Some exception to change");
        }

        return new PaymentInfoDTO(
                licensePlate,
                infoTillNow.timeSpentInHours,
                infoTillNow.amountTillNow,
                vehicle.getEuroCategory()
        );
    }

    @Override
    public VehicleLeaveDTO requestLeave(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate).orElseThrow(() -> new NoSuchVehicleException(String.format("Vehicle with licensePlate %s is not found", licensePlate)));

        HttpEntity<GetPaymentInfoDTO> getPaymentInfoDTOHttpEntity = getGetPaymentInfoDTOHttpEntity(vehicle);

        ResponseEntity<PaymentInfoResponse> paymentInfoResponseResponseEntity = restTemplate.exchange(
                GET_PAYMENT_INFO_URL,
                HttpMethod.POST,
                getPaymentInfoDTOHttpEntity,
                PaymentInfoResponse.class);

        vehicle.getParkingSession().setLeftAt(LocalDateTime.now());
        Vehicle save = vehicleRepo.save(vehicle);

        PaymentInfoResponse paymentInfoResponse = paymentInfoResponseResponseEntity.getBody();
        if (paymentInfoResponse == null) {
            throw new RuntimeException("Unsuccessful payment");
        }

        return (VehicleLeaveDTO) new VehicleLeaveDTO()
                .setTimeSpent(paymentInfoResponse.timeSpentInHours())
                .setPaidAmount(paymentInfoResponse.amountTillNow)
                .setCurrency(CurrencyEnum.EUR)
                .setLicensePlate(save.getLicensePlate())
                .setEnteredAt(save.getParkingSession().getEnteredAt())
                .setLeftAt(save.getParkingSession().getLeftAt())
                .setEuroCategory(save.getEuroCategory());
    }

    private static HttpEntity<GetPaymentInfoDTO> getGetPaymentInfoDTOHttpEntity(Vehicle vehicle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpHeaders httpHeaders = new HttpHeaders();

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            String token = jwtAuthenticationToken.getToken().getTokenValue();
            httpHeaders.setBearerAuth(token);
        }

        HttpEntity<GetPaymentInfoDTO> httpEntity = new HttpEntity<>(
                new GetPaymentInfoDTO(vehicle.getParkingSession().getEnteredAt(), LocalDateTime.now(), 2.0),
                httpHeaders);
        return httpEntity;
    }

//
//    }

    private record GetPaymentInfoDTO(LocalDateTime enteredAt, LocalDateTime leftAt, double ratePerHour){}
    private record PaymentInfoResponse(long timeSpentInHours, BigDecimal amountTillNow){}

    private static class VehicleMapper {
        private static VehicleGeneralDTO mapGeneralVehicleDTO(Vehicle vehicle) {
            return VehicleGeneralDTO.Builder.builder()
                    .setLicencePlate(vehicle.getLicensePlate())
                    .setVehicleType((vehicle instanceof CarEntity) ? Vehicle.VehicleType.CAR : Vehicle.VehicleType.TRUCK)
                    .setEnteredAt(vehicle.getParkingSession().getEnteredAt())
                    .build();
        }
    }
}
