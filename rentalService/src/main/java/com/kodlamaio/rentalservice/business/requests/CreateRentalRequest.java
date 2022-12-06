package com.kodlamaio.rentalservice.business.requests;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    @NotNull
    private String carId;
    @NotNull
    private int rentedForDays;
    @NotNull
    @Min(0)
    private double dailyPrice;
}
