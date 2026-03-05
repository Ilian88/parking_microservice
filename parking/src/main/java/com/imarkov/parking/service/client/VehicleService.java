package com.imarkov.parking.service.client;

import com.imarkov.parking.external.PaymentInfoGateAway;
import com.imarkov.parking.model.StayDetails;
import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dto.*;
import com.imarkov.parking.service.VehicleServiceImpl;

import java.util.List;

public interface VehicleService {
    List<VehicleGeneralDTO> getAllCurrent();
    VehicleCreatedDTO createVehicle(VehicleEnterDTO vehicleCreateDTO);
    PaymentInfoDTO getPaymentInfo(String licensePlate);
    StayDetails requestLeave(String licensePlate);
}
