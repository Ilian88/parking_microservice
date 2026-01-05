package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.dao.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarEnterDTO extends VehicleEnterDTO {
    @NotBlank(message = "model cannot be empty")
    private String model;
    @NotBlank(message = "make cannot be empty")
    private String make;
    private String color;

    public String getModel() {
        return model;
    }

    public CarEnterDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarEnterDTO setMake(String make) {
        this.make = make;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CarEnterDTO setColor(String color) {
        this.color = color;
        return this;
    }
}