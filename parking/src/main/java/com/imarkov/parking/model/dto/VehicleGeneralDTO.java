package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.dao.Vehicle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record VehicleGeneralDTO(String licencePlate,
                                Vehicle.VehicleType vehicleType,
                                LocalDateTime enteredAt,
                                long stayUntilNow) {

    public static class Builder {
        private String licencePlate;
        private Vehicle.VehicleType vehicleType;
        private static LocalDateTime enteredAt;
        private long stayUntilNow;

        public Builder setLicencePlate(String licencePlate) {
            this.licencePlate = licencePlate;
            return this;
        }

        public Builder setVehicleType(Vehicle.VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setEnteredAt(LocalDateTime newEnteredAt) {
            enteredAt = newEnteredAt;
            return this;
        }

        public Builder setStayUntilNow(long stayUntilNow) {
            this.stayUntilNow = stayUntilNow;
            return this;
        }

        public VehicleGeneralDTO build() {
            if (enteredAt == null) {
                throw new IllegalStateException("enteredAt field is required!");
            }

            return new VehicleGeneralDTO(
                    licencePlate,
                    vehicleType,
                    enteredAt,
                    calculateTimeSpentTillNow()
                    );
        }

        public static Builder builder(){
            return new Builder();
        }
        private static long calculateTimeSpentTillNow() {
            return ChronoUnit.MINUTES.between(Builder.enteredAt, LocalDateTime.now());
        }
    }
}
