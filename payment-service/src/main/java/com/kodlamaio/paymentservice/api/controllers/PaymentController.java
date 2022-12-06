package com.kodlamaio.paymentservice.api.controllers;

import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.get.PaymentRequest;
import com.kodlamaio.paymentservice.business.requests.update.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetAllPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.update.UpdatePaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@Valid @RequestBody UpdatePaymentRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PostMapping("/check")
    public void checkIfPaymentSuccessful(
            @RequestParam String cardNumber,
            @RequestParam String fullName,
            @RequestParam int cardExpirationYear,
            @RequestParam int cardExpirationMonth,
            @RequestParam String cardCvv,
            @RequestParam double price) {
        PaymentRequest request =
                new PaymentRequest(cardNumber,
                                   fullName,
                                   cardExpirationYear,
                                   cardExpirationMonth,
                                   cardCvv,
                                   price);
        service.checkIfPaymentSuccessful(request);
    }
}
