package com.kodlamaio.paymentservice.business.abstracts;

import com.kodlamaio.paymentservice.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.get.PaymentRequest;
import com.kodlamaio.paymentservice.business.requests.update.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetAllPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.update.UpdatePaymentResponse;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentResponse> getAll();
    GetPaymentResponse getById(String id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UpdatePaymentRequest request, String id);
    void delete(String id);
    void checkIfPaymentSuccessful(PaymentRequest request);
}
