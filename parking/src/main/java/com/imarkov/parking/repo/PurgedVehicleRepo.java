package com.imarkov.parking.repo;

import com.imarkov.parking.model.PurgedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurgedVehicleRepo extends JpaRepository<PurgedVehicle, Long> {
    Optional<List<PurgedVehicle>> findByParkingSession_EnteredAtBetween (LocalDateTime start, LocalDateTime end);
}
