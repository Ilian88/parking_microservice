package com.imarkov.parking.booking;

import com.imarkov.parking.model.dao.ParkingBooking;
import com.imarkov.parking.model.dto.BookingDTO;
import com.imarkov.parking.repo.ParkingBookingRepo;
import com.imarkov.parking.service.client.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookingManagerServiceImpl implements BookingManagerService {
    private final ParkingBookingRepo parkingBookingRepo;
    private final ModelMapper modelMapper;
    private final ParkingSlotCache parkingSlotCache;

    public BookingManagerServiceImpl(VehicleService vehicleService, ParkingBookingRepo parkingBookingRepo, ModelMapper modelMapper,
                                     ParkingSlotCache parkingCache) {
        this.parkingBookingRepo = parkingBookingRepo;
        this.modelMapper = modelMapper;
        this.parkingSlotCache = parkingCache;
    }

    @Override
    public int getFreeSpaces() {
        return parkingSlotCache.getAvailableSlots();
    }

    @Override
    public int bookSlot(BookingDTO bookingDTO) {
        ParkingBooking parkingBooking = modelMapper.map(bookingDTO, ParkingBooking.class);

        parkingBookingRepo.save(parkingBooking);

        return parkingSlotCache.addSlot();
    }
}
