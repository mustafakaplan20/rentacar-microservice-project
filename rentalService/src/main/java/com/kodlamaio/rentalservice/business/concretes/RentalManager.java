package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.common.events.payments.PaymentReceiveEvent;
import com.kodlamaio.common.events.rentals.RentalCreateEvent;
import com.kodlamaio.common.events.rentals.RentalDeleteEvent;
import com.kodlamaio.common.events.rentals.RentalUpdateEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.rentalservice.business.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.UpdateRentalResponse;
import com.kodlamaio.rentalservice.client.CarClient;
import com.kodlamaio.rentalservice.client.PaymentClient;
import com.kodlamaio.rentalservice.dataAccess.RentalRepository;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.kafka.RentalProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository repository;
    private ModelMapperService mapper;
    private RentalProducer rentalProducer;
    private CarClient carClient;
    private PaymentClient paymentClient;

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalResponse> responses = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetRentalResponse getById(String id) {
        checkIfRentalExists(id);
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request, CreatePaymentRequest paymentRequest) {
        carClient.checkIfCarIsAvailable(request.getCarId());
        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(UUID.randomUUID().toString());
        rental.setDateStarted(LocalDateTime.now());
        double totalPrice = request.getRentedForDays() * request.getDailyPrice();
        rental.setTotalPrice(totalPrice);
        paymentClient.checkIfPaymentSuccessful(paymentRequest.getCardNumber(),
                paymentRequest.getFullName(),
                paymentRequest.getCardExpirationYear(),
                paymentRequest.getCardExpirationMonth(),
                paymentRequest.getCardCvv(),
                totalPrice);
        repository.save(rental);

        RentalCreateEvent rentalCreatedEvent = new RentalCreateEvent();
        rentalCreatedEvent.setCarId(rental.getCarId());
        rentalCreatedEvent.setMessage("Rental Created");
        rentalProducer.sendMessage(rentalCreatedEvent);

        PaymentReceiveEvent paymentReceivedEvent = new PaymentReceiveEvent();
        paymentReceivedEvent.setCarId(rental.getCarId());
        paymentReceivedEvent.setFullName(paymentRequest.getFullName());
        paymentReceivedEvent.setDailyPrice(request.getDailyPrice());
        paymentReceivedEvent.setTotalPrice(totalPrice);
        paymentReceivedEvent.setRentedForDays(request.getRentedForDays());
        paymentReceivedEvent.setRentedAt(rental.getDateStarted());
        rentalProducer.sendMessage(paymentReceivedEvent);
        CreateRentalResponse response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    @Override
    public UpdateRentalResponse update(UpdateRentalRequest request, String id) {
        checkIfRentalExists(id);
        carClient.checkIfCarIsAvailable(request.getCarId());
        RentalUpdateEvent rentalUpdatedEvent = new RentalUpdateEvent();
        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        rentalUpdatedEvent.setOldCarId(repository.findById(id).orElseThrow().getCarId());
        repository.save(rental);
        rentalUpdatedEvent.setNewCarId(rental.getCarId());
        rentalUpdatedEvent.setMessage("Rental Updated");
        rentalProducer.sendMessage(rentalUpdatedEvent);
        UpdateRentalResponse response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfRentalExists(id);
        RentalDeleteEvent event = new RentalDeleteEvent();
        event.setCarId(repository.findById(id).orElseThrow().getCarId());
        event.setMessage("Rental Deleted");
        rentalProducer.sendMessage(event);
        repository.deleteById(id);
    }

    private void checkIfRentalExists(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("RENTAL.NOT_FOUND");
        }
    }
}
