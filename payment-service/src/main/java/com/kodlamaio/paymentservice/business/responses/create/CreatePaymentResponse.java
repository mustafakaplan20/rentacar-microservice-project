package com.kodlamaio.paymentservice.business.responses.create;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private String id;
    private String cardNumber;
    private String fullName;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    private String cardCvv;
    private double balance;
}
