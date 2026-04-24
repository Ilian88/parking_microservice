package com.imarkov.parking;

import com.imarkov.parking.model.dao.CarEntity;
import com.imarkov.parking.model.dao.ParkingSession;
import com.imarkov.parking.model.dao.Vehicle;
import com.imarkov.parking.model.dao.user.Role;
import com.imarkov.parking.model.dao.user.UserEntity;
import com.imarkov.parking.repo.VehicleRepo;
import com.imarkov.parking.util.VehicleFactory;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.imarkov.parking.repo.UserRepo;
import org.springframework.transaction.annotation.Transactional;
//import com.imarkov.parking_logger.ParkingLogger;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DBInitializer {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Logger logger = LoggerFactory.getLogger(DBInitializer.class);
//    private final ParkingLogger parkingLogger;

    private final UserRepo userRepo;
    private final VehicleRepo vehicleRepo;
    private final PasswordEncoder passwordEncoder;

    public DBInitializer(UserRepo userRepo, VehicleRepo vehicleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.vehicleRepo = vehicleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationReady () {
//        executorService.submit(()-> {
            if (!userRepo.existsByUsername("ilian88").get()) {
                UserEntity user = new UserEntity();
                user.setUsername("ilian88");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setRole(Role.ADMIN);

                try {
                    userRepo.save(user);
                } catch (Exception ex) {
                    logger.error("********* Error occurred -> {}", ex.getMessage());
                }
                logger.info("User {} was created successfully with role {}.", user.toString(), user.getRole());
            }
            if (!vehicleRepo.existsByLicensePlate("Test").get()) {
                CarEntity carEntity = (CarEntity) VehicleFactory.CAR.create();
                carEntity.setMake("Mercedes");
                carEntity.setLicensePlate("Test");
                carEntity.setEuroCategory(Vehicle.EuroCategory.THREE);

                ParkingSession parkingSession = new ParkingSession();
//                parkingSession.setVehicle(carEntity);
                parkingSession.setEnteredAt(LocalDateTime.now());

                carEntity.setParkingSession(parkingSession);

                vehicleRepo.save(carEntity);
                logger.info("Vehicle {} was created successfully.", carEntity.toString());
            }
//        });
    }

    @PreDestroy
    public void shutDown() {
        executorService.shutdown();
    }
}
