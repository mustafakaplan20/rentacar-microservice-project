package com.kodlamaio.common.events.rentals;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalUpdateEvent {
    private String message;
    private String newCarId;
    private String oldCarId;
}
