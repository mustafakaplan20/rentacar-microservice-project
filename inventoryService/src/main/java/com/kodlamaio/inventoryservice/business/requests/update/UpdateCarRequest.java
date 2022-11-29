package com.kodlamaio.inventoryservice.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
    @Min(0)
    private double dailyPrice;

    @Min(2015)
    private int modelYear;

    @NotNull
    @NotBlank
    private String plate;

    @Min(1)
    @Max(3)
    private int state; //1-Available, 2- Under Maintanence, 3- Rented

    @NotBlank
    @NotNull
    private String modelId;
}
