package com.kodlamaio.common.events.rentals;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalCreateEvent {
    private String message;
    private String carId;
}