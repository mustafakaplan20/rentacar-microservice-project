package com.kodlamaio.filterservice.business.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllFilterResponse {
    private String id;
    private String carId;
    private String modelId;
    private String brandId;
    private String modelName;
    private String brandName;
    private double dailyPrice;
    private int modelYear;
    private String plate;
    private int state;
}
