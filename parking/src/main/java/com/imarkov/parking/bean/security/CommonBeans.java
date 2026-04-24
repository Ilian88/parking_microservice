package com.imarkov.parking.bean.security;

import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dao.user.AccountType;
import com.imarkov.parking.model.dao.user.Role;
import com.imarkov.parking.model.dao.user.UserEntity;
import com.imarkov.parking.model.dto.RegisterDTO;
import com.imarkov.parking.model.dto.VehicleGeneralDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        Converter<RegisterDTO, UserEntity> registerDtoToEntity = ctx -> {
            UserEntity userEntity = ctx.getDestination() != null
                    ? ctx.getDestination()
                    : new UserEntity();
            RegisterDTO source = ctx.getSource();

            userEntity
                    .setUsername(source.username())
                    .setAccountType(AccountType.valueOf(source.accountType().toUpperCase()))
                    .setPassword(passwordEncoder().encode(source.password()))
                    .setEmail(source.email())
                    .setRole(Role.USER)
                    .setCompanyName(source.companyName())
                    .setPhone(source.phone());

            return userEntity;
        };

        modelMapper
                .typeMap(RegisterDTO.class, UserEntity.class)
                .setConverter(registerDtoToEntity);

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
