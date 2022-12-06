package com.kodlamaio.common.events.inventories.cars;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDeleteEvent {
    private String carId;
}