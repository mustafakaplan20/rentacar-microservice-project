package com.kodlamaio.inventoryservice.kafka;

import com.kodlamaio.common.CarState;
import com.kodlamaio.common.events.rentals.*;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RentalConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
    private final CarService carService;
    private final InventoryProducer producer;

    public RentalConsumer(CarService carService, InventoryProducer producer) {
        this.carService = carService;
        this.producer = producer;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "rental-create"
    )
    public void consume(RentalCreateEvent event) {
        carService.changeState(CarState.RENTED, event.getCarId());
        CarRentalCreateEvent carRentalCreateEvent = new CarRentalCreateEvent();
        carRentalCreateEvent.setCarId(event.getCarId());
        carRentalCreateEvent.setMessage("Car rented!");
        producer.sendMessage(carRentalCreateEvent);
        LOGGER.info("Car rented!");
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "rental-update")
    public void consume(RentalUpdateEvent event) {
        carService.changeState(CarState.AVAILABLE, event.getOldCarId());
        carService.changeState(CarState.RENTED, event.getNewCarId());
        CarRentalUpdateEvent carRentalUpdateEvent = new CarRentalUpdateEvent();
        carRentalUpdateEvent.setNewCarId(event.getNewCarId());
        carRentalUpdateEvent.setOldCarId(event.getOldCarId());
        carRentalUpdateEvent.setMessage("Car rented state updated!");
        producer.sendMessage(carRentalUpdateEvent);
        LOGGER.info("Car rented state updated!");
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "rental-delete")
    public void consume(RentalDeleteEvent event) {
        carService.changeState(CarState.AVAILABLE, event.getCarId());
        CarRentalDeleteEvent carRentalDeleteEvent = new CarRentalDeleteEvent();
        carRentalDeleteEvent.setCarId(event.getCarId());
        carRentalDeleteEvent.setMessage("Car deleted from rental!");
        producer.sendMessage(carRentalDeleteEvent);
        LOGGER.info("Car deleted from rental!");
    }
}
