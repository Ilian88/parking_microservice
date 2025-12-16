package com.imarkov.parking.repo;

import com.imarkov.parking.model.dao.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    Optional<Boolean> existsByLicensePlate(String licensePlate);

    @Query("SELECT v FROM Vehicle v WHERE v.parkingSession.leftAt IS NULL AND v.licensePlate = :licensePlate")
    Optional<Vehicle> findByLicensePlate(String licensePlate);

    @Query("SELECT v FROM Vehicle v WHERE v.parkingSession.leftAt IS NULL")
    Optional<List<Vehicle>> findAllThatInParking();

    @Transactional
    @Modifying
    @Query("DELETE FROM Vehicle v WHERE v.parkingSession.leftAt IS NOT NULL")
    int deleteVehiclesNotInParking();

    @Query("SELECT v FROM Vehicle v WHERE v.parkingSession.leftAt IS NOT NULL")
    List<Vehicle> findAllVehicleLeft();
}
