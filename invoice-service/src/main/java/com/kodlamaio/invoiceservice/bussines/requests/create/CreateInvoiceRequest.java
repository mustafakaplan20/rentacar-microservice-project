package com.kodlamaio.invoiceservice.bussines.requests.create;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    @NotBlank
    private String carId;
    @NotBlank
    private String fullName;
    @NotBlank
    private String modelName;
    @NotBlank
    private String brandName;
    @NotNull
    @Min(1886)
    private int modelYear;
    @Min(0)
    private double dailyPrice;
    @Min(0)
    private double totalPrice;
    @Min(0)
    private int rentedForDays;
}