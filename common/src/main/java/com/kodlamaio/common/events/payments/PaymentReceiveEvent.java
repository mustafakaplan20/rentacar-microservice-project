package com.kodlamaio.common.events.payments;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReceiveEvent {
    private String carId;
    private String fullName;
    private String modelName;
    private String brandName;
    private int modelYear;
    private double dailyPrice;
    private double totalPrice;
    private int rentedForDays;
    private LocalDateTime rentedAt;
}
