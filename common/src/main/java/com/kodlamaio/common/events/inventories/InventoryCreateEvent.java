package com.kodlamaio.common.events.inventories;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCreateEvent {
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
