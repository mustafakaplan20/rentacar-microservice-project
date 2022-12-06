package com.kodlamaio.rentalservice.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
    @NotNull
    private String carId;
    @NotNull
    private LocalDateTime dateStarted;
    @NotNull
    private int rentedForDays;
    @NotNull
    @Min(0)
    private double dailyPrice;
}
