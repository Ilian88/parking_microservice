package com.imarkov.parking.service;

import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.ParkingSession;
import com.imarkov.parking.model.dao.TruckEntity;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dto.CarEnterDTO;
import com.imarkov.parking.model.dto.TruckEnterDTO;

import java.time.LocalDateTime;

public class VehicleDTOMapper {

    private VehicleDTOMapper(){}

    public static CarEntity mapCreateCarDtoToEntity(CarEnterDTO dto) {
        CarEntity carEntity = new CarEntity()
                .setMake(dto.getMake())
                .setModel(dto.getModel());

        carEntity.setLicensePlate(dto.getLicensePlate());
        carEntity.setEuroCategory(dto.getEuroCategory());
        ParkingSession parkingSession = new ParkingSession();

//        parkingSession.setVehicle(carEntity);
        carEntity.setParkingSession(parkingSession);

        return carEntity;
    }

    public static TruckEntity mapCreateTruckDtoToEntity(TruckEnterDTO truckEnterDTO) {
        ParkingSession parkingSession = new ParkingSession();

        TruckEntity truckEntity = (TruckEntity) new TruckEntity()
                .setLicensePlate(truckEnterDTO.getLicensePlate())
                .setEuroCategory(truckEnterDTO.getEuroCategory());

        truckEntity
                .setTruckMake(truckEnterDTO.getTruckMake())
                .setTrailerMake(truckEnterDTO.getTrailerMake())
                .setTruckModel(truckEnterDTO.getTruckModel())
                .setTrailerModel(truckEnterDTO.getTrailerModel())
                .setWeight(truckEnterDTO.getWeight())
                .setParkingSession(parkingSession);

        return truckEntity;
    }
}
