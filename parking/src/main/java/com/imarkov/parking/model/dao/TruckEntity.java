package com.imarkov.parking.model.dao;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Qualifier("truck")
public class TruckEntity extends Vehicle {
    private String truckMake;
    private String truckModel;
    private String trailerMake;
    private String trailerModel;
    private String trailerLicense;
    private double weight;
    private int lengthOfComposition;

    public String getTruckMake() {
        return truckMake;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public String getTrailerMake() {
        return trailerMake;
    }

    public String getTrailerModel() {
        return trailerModel;
    }

    public double getWeight() {
        return weight;
    }

    public int getLengthOfComposition() {
        return lengthOfComposition;
    }


    public String getTrailerLicense() {
        return trailerLicense;
    }

    public TruckEntity setTruckMake(String truckMake) {
        this.truckMake = truckMake;
        return this;
    }

    public TruckEntity setTruckModel(String truckModel) {
        this.truckModel = truckModel;
        return this;
    }

    public TruckEntity setTrailerMake(String trailerMake) {
        this.trailerMake = trailerMake;
        return this;
    }

    public TruckEntity setTrailerModel(String trailerModel) {
        this.trailerModel = trailerModel;
        return this;
    }

    public TruckEntity setTrailerLicense(String trailerLicense) {
        this.trailerLicense = trailerLicense;
        return this;
    }

    public TruckEntity setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public TruckEntity setLengthOfComposition(int lengthOfComposition) {
        this.lengthOfComposition = lengthOfComposition;
        return this;
    }
}
