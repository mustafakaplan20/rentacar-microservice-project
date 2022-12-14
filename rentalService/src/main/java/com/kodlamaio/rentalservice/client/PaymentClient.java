package com.kodlamaio.rentalservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "paymentclient", url = "http://localhost:9010/")
public interface PaymentClient {
    @RequestMapping(method = RequestMethod.POST, value = "payment-service/api/payments/check")
    void checkIfPaymentSuccessful(
            @RequestParam String cardNumber,
            @RequestParam String fullName,
            @RequestParam int cardExpirationYear,
            @RequestParam int cardExpirationMonth,
            @RequestParam String cardCvv,
            @RequestParam double price);
}
