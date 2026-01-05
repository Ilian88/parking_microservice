package com.imarkov.parking.service.client;

import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dto.*;

import java.util.List;

public interface VehicleService {
    List<VehicleGeneralDTO> getAllCurrent();
    VehicleCreatedDTO createVehicle(VehicleEnterDTO vehicleCreateDTO);
    PaymentInfoDTO getPaymentInfo(String licensePlate);
    VehicleLeaveDTO requestLeave(String licensePlate);
}
