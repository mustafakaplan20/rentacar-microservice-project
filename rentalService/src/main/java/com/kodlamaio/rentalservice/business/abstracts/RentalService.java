package com.kodlamaio.rentalservice.business.abstracts;

import com.kodlamaio.rentalservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalservice.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalResponse> getAll();
    GetRentalResponse getById(String id);
    CreateRentalResponse add(CreateRentalRequest request, CreatePaymentRequest paymentRequest);
    UpdateRentalResponse update(UpdateRentalRequest request, String id);
    void delete(String id);

}
