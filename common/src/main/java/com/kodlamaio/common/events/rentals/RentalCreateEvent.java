package com.kodlamaio.common.events.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreateEvent {
    private String message;
    private String carId;
    //tek ihtiyacın car id , bununla state değişir
}
