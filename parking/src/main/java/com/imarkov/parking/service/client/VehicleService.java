package com.imarkov.parking.service.client;

import com.imarkov.parking.model.dao.PaymentInfoDTO;
import com.imarkov.parking.model.dto.VehicleCreatedDTO;
import com.imarkov.parking.model.dto.VehicleEnterDTO;
import com.imarkov.parking.model.dto.VehicleGeneralDTO;
import com.imarkov.parking.model.dto.VehicleLeaveDTO;

import java.util.List;

public interface VehicleService {
    List<VehicleGeneralDTO> getAllCurrent();
    VehicleCreatedDTO createVehicle(VehicleEnterDTO vehicleCreateDTO);
    PaymentInfoDTO getPaymentInfo(String licensePlate);
    VehicleLeaveDTO requestLeave(String licensePlate);
}
