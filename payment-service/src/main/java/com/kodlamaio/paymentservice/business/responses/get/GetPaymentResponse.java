package com.kodlamaio.paymentservice.business.responses.get;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentResponse {
    private String id;
    private String cardNumber;
    private String fullName;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    private String cardCvv;
    private double balance;
}
