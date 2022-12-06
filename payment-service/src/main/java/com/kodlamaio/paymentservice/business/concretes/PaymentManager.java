package com.kodlamaio.paymentservice.business.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.create.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.requests.get.PaymentRequest;
import com.kodlamaio.paymentservice.business.requests.update.UpdatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.create.CreatePaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetAllPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.get.GetPaymentResponse;
import com.kodlamaio.paymentservice.business.responses.update.UpdatePaymentResponse;
import com.kodlamaio.paymentservice.entities.Payment;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllPaymentResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentResponse> response = payments
                .stream()
                .map(payment -> mapper.forResponse().map(payment, GetAllPaymentResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(String id) {
        checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.forResponse().map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        checkIfCardNumberExists(request.getCardNumber());
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(UUID.randomUUID().toString());
        repository.save(payment);
        CreatePaymentResponse response = mapper.forResponse().map(payment, CreatePaymentResponse.class);

        return response;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest request, String id) {
        checkIfPaymentExists(id);
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.forResponse().map(payment, UpdatePaymentResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void checkIfPaymentSuccessful(PaymentRequest request) {
        checkPayment(request);
    }

    private void checkPayment(PaymentRequest request) {
        if (!repository.existsByCardNumberAndFullNameAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getFullName(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv())) {
            throw new BusinessException("NOT_A_VALID_PAYMENT!");
        } else {
            double balance = repository.findByCardNumber(request.getCardNumber()).getBalance();
            if (balance < request.getPrice()) {
                throw new BusinessException("NOT_ENOUGH_MONEY!");
            } else {
                Payment payment = repository.findByCardNumber(request.getCardNumber());
                payment.setBalance(balance - request.getPrice());
                repository.save(payment);
            }
        }
    }

    private void checkIfPaymentExists(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("PAYMENT_NOT_FOUND!");
        }
    }

    private void checkIfCardNumberExists(String cardNumber) {
        if (repository.existsByCardNumber(cardNumber)) {
            throw new BusinessException("CARD_NUMBER_ALREADY_EXISTS!");
        }
    }
}
