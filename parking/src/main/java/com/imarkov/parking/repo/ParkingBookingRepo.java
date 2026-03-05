package com.imarkov.parking.repo;

import com.imarkov.parking.model.dao.ParkingBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingBookingRepo extends JpaRepository<ParkingBooking, Long> {

}
