package com.imarkov.parking.repo;

import com.imarkov.parking.model.PurgedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurgedVehicleRepo extends JpaRepository<PurgedVehicle, Long> {

}
