package com.imarkov.parking.bean.security;

import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dto.VehicleGeneralDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonBeans {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

//        Converter<Vehicle, VehicleGeneralDTO> vehicleGeneralDtoConverter = ctx -> {
//            Vehicle source = ctx.getSource();
//
//            return VehicleGeneralDTO.Builder.builder()
//                    .setLicencePlate(source.getLicensePlate())
//                    .setVehicleType((source instanceof CarEntity) ? Vehicle.VehicleType.CAR : Vehicle.VehicleType.TRUCK)
//                    .setEnteredAt(source.getParkingSession().getEnteredAt())
//                    .build();
//        };
//
//        modelMapper.addConverter(vehicleGeneralDtoConverter);

        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
