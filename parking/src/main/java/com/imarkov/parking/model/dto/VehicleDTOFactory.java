package com.imarkov.parking.model.dto;

import java.lang.reflect.InvocationTargetException;

public class VehicleDTOFactory<T extends VehicleDTO> {

    public final Class<T> type;

    public VehicleDTOFactory(Class<T> type) {
        this.type = type;
    }

    public T createDTO() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to create DTO", e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to create DTO", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to create DTO", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to create DTO", e);
        }
    }
}
