package com.imarkov.parking.service;

import com.imarkov.parking.exception.NoSuchVehicleException;
import com.imarkov.parking.exception.VehicleAlreadyExistsException;
import com.imarkov.parking.external.PaymentInfoGateAway;
import com.imarkov.parking.model.CurrencyEnum;
import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dto.*;
import com.imarkov.parking.remote.ParkingHistoryGateway;
import com.imarkov.parking.remote.ParkingHistoryGatewayImpl;
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
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private final PaymentInfoGateAway paymentInfoGateAway;
//    private static final String GET_PAYMENT_INFO_URL = "http://localhost:8081/payment/info";

    private final VehicleRepo vehicleRepo;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    public VehicleServiceImpl(PaymentInfoGateAway paymentInfoGateAway, VehicleRepo vehicleRepo, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.paymentInfoGateAway = paymentInfoGateAway;
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
        if (vehicleRepo.findByLicensePlate(vehicleEnterDTO.getLicensePlate()).orElse(null) != null) {
            throw new VehicleAlreadyExistsException("Vehicle with licensePlate " + vehicleEnterDTO.getLicensePlate() + " already exists in the parking");
        }
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

        PaymentInfoGateAway.PaymentInfoResponse infoTillNow = paymentInfoGateAway.getInfoTillNow(vehicle);

        if (infoTillNow == null) {
            throw new RuntimeException("Some exception to change");
        }

        return new PaymentInfoDTO(
                licensePlate,
                infoTillNow.timeSpentInHours(),
                infoTillNow.amountTillNow(),
                vehicle.getEuroCategory()
        );
    }

    @Override
    public VehicleLeaveDTO requestLeave(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findByLicensePlate(licensePlate).orElseThrow(() -> new NoSuchVehicleException(String.format("Vehicle with licensePlate %s is not found", licensePlate)));

        PaymentInfoGateAway.PaymentInfoResponse paymentInfoResponse = paymentInfoGateAway.getInfoTillNow(vehicle);
        vehicle.getParkingSession().setLeftAt(LocalDateTime.now());

        Vehicle save = vehicleRepo.save(vehicle);

        if (paymentInfoResponse == null) {
            throw new RuntimeException("Unsuccessful payment");
        }

        VehicleLeaveDTO vehicleLeaveDTO = getVehicleLeaveDTO(paymentInfoResponse, save);

        new ParkingHistoryGatewayImpl(vehicleLeaveDTO, UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8082)
                .pathSegment("history", "create")
                .build()
                .toString())
                .sendVehicle();

        return vehicleLeaveDTO;
    }

    private static VehicleLeaveDTO getVehicleLeaveDTO(PaymentInfoGateAway.PaymentInfoResponse paymentInfoResponse, Vehicle save) {
        VehicleLeaveDTO vehicleLeaveDTO = (VehicleLeaveDTO) new VehicleLeaveDTO()
                .setTimeSpent(paymentInfoResponse.timeSpentInHours())
                .setPaidAmount(paymentInfoResponse.amountTillNow())
                .setCurrency(CurrencyEnum.EUR)
                .setLicensePlate(save.getLicensePlate())
                .setEnteredAt(save.getParkingSession().getEnteredAt())
                .setLeftAt(save.getParkingSession().getLeftAt())
                .setEuroCategory(save.getEuroCategory());
        return vehicleLeaveDTO;
    }

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
