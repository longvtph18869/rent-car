package com.example.rent.DTO;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import com.example.rent.enums.CarColor;
import com.example.rent.enums.CarType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDTO {
	@NotBlank
    private String licensePlates;
    @NotBlank
    private String name;
    @Min(1980)
    private int yearOfManufacture;
    @NotBlank
    private CarColor color;
    @NotBlank
    private int type;
    @Positive
    private BigDecimal rentalPrice;
    @NotBlank
    private String description;
    @NotNull
    private int manufacturerId;
    @NotBlank
    private String location;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
	@NotEmpty
    private List<String> carImages;

}
