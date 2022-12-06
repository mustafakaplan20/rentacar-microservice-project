package com.kodlamaio.rentalservice.api.controllers;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalservice.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.UpdateRentalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalController {
    private final RentalService service;
    Logger logger = LoggerFactory.getLogger(RentalController.class);

    public RentalController(RentalService service) {
        this.service = service;
    }

    @GetMapping
    public List<GetAllRentalResponse> getAll() {
        logger.info("Getting all rentals");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateRentalResponse add(
            @Valid @RequestBody CreateRentalRequest request,
            @RequestParam String cardNumber,
            @RequestParam String fullName,
            @RequestParam int cardExpirationYear,
            @RequestParam int cardExpirationMonth,
            @RequestParam String cardCvv) {
        logger.info("Adding new rental");
        CreatePaymentRequest paymentRequest =
                new CreatePaymentRequest(cardNumber,
                        fullName,
                        cardExpirationYear,
                        cardExpirationMonth,
                        cardCvv);
        return service.add(request, paymentRequest);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@Valid @RequestBody UpdateRentalRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
