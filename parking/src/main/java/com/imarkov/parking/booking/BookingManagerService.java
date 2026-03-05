package com.imarkov.parking.booking;

import com.imarkov.parking.model.dto.BookingDTO;

public interface BookingManagerService {
    int getFreeSpaces();
    int bookSlot(BookingDTO bookingDTO);

}
