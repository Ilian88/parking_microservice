package com.imarkov.parking.model.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
//    @NotNull(message = "Type cannot be null")
    @Pattern(regexp = "(?i)^(CAR|TRUCK)$",
            message = "Vehicle type must be CAR or TRUCK")
    private String type;

    public String getType() {
        return type;
    }

    public VehicleEnterDTO setType(String type) {
        this.type = type;
        return this;
    }
}
