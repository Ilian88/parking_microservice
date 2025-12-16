package com.imarkov.parking.model.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarEnterDTO.class, name = "CAR"),
        @JsonSubTypes.Type(value = TruckEnterDTO.class, name = "TRUCK")
                })
public class VehicleEnterDTO extends VehicleDTO {
    private String type;

    public String getType() {
        return type;
    }

    public VehicleEnterDTO setType(String type) {
        this.type = type;
        return this;
    }
}
