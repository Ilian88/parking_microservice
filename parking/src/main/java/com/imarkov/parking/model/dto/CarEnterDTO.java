package com.imarkov.parking.model.dto;

import com.imarkov.parking.model.dao.Vehicle;

public class CarEnterDTO extends VehicleEnterDTO {
    private String model;
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