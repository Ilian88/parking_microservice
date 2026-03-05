package com.imarkov.parking.controller;

import com.imarkov.parking.booking.BookingManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingManagerService bookingManagerService;

    public BookingController(BookingManagerService bookingManagerService) {
        this.bookingManagerService = bookingManagerService;
    }

    @GetMapping("/slots/free")
    public int getAvailableSpaces() {
        return bookingManagerService.getFreeSpaces();
    }
}
