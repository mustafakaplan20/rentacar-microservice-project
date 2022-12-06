package com.kodlamaio.invoiceservice.bussines.responses.get;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoiceResponse {
    private String id;
    private String carId;
    private String fullName;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private double totalPrice;
    private int rentedForDays;
}