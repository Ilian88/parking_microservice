package com.imarkov.parking.model.dto;

public class TruckEnterDTO extends VehicleEnterDTO {
    private String trailerNumber;
    private String truckMake;
    private String truckModel;
    private String trailerModel;
    private String trailerMake;
    private double weight;
    private boolean isADR;
    private boolean isOverSized;

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public TruckEnterDTO setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
        return this;
    }

    public String getTruckMake() {
        return truckMake;
    }

    public TruckEnterDTO setTruckMake(String truckMake) {
        this.truckMake = truckMake;
        return this;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public TruckEnterDTO setTruckModel(String truckModel) {
        this.truckModel = truckModel;
        return this;
    }

    public String getTrailerModel() {
        return trailerModel;
    }

    public TruckEnterDTO setTrailerModel(String trailerModel) {
        this.trailerModel = trailerModel;
        return this;
    }

    public String getTrailerMake() {
        return trailerMake;
    }

    public TruckEnterDTO setTrailerMake(String trailerMake) {
        this.trailerMake = trailerMake;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public TruckEnterDTO setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public boolean isADR() {
        return isADR;
    }

    public TruckEnterDTO setADR(boolean ADR) {
        isADR = ADR;
        return this;
    }

    public boolean isOverSized() {
        return isOverSized;
    }

    public TruckEnterDTO setOverSized(boolean overSized) {
        isOverSized = overSized;
        return this;
    }
}
