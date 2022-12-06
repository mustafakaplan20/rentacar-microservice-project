package com.kodlamaio.filterservice.kafka;

import com.kodlamaio.common.CarState;
import com.kodlamaio.common.events.inventories.InventoryCreateEvent;
import com.kodlamaio.common.events.inventories.brands.BrandDeleteEvent;
import com.kodlamaio.common.events.inventories.brands.BrandUpdateEvent;
import com.kodlamaio.common.events.inventories.cars.CarDeleteEvent;
import com.kodlamaio.common.events.inventories.cars.CarUpdateEvent;
import com.kodlamaio.common.events.inventories.models.ModelDeleteEvent;
import com.kodlamaio.common.events.inventories.models.ModelUpdateEvent;
import com.kodlamaio.common.events.rentals.CarRentalCreateEvent;
import com.kodlamaio.common.events.rentals.CarRentalDeleteEvent;
import com.kodlamaio.common.events.rentals.CarRentalUpdateEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryConsumer.class);
    private final FilterService service;
    private final ModelMapperService mapper;

    public InventoryConsumer(FilterService service, ModelMapperService mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "inventory-create"
    )
    public void consume(InventoryCreateEvent event) {
        Filter filter = mapper.forRequest().map(event, Filter.class);
        service.save(filter);
        LOGGER.info("Inventory created event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-delete"
    )
    public void consume(CarDeleteEvent event) {
        service.delete(event.getCarId());
        LOGGER.info("Car deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-update"
    )
    public void consume(CarUpdateEvent event) {
        Filter filter = mapper.forRequest().map(event, Filter.class);
        String id = service.getByCarId(event.getCarId()).getId();
        filter.setId(id);
        service.save(filter);
        LOGGER.info("Car updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "brand-delete"
    )
    public void consume(BrandDeleteEvent event) {
        service.deleteAllByBrandId(event.getBrandId());

        LOGGER.info("Brand deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "brand-update"
    )
    public void consume(BrandUpdateEvent event) {
        service.getByBrandId(event.getId()).forEach(filter -> {
            filter.setBrandName(event.getName());
            service.save(filter);
        });

        LOGGER.info("Brand updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "model-delete"
    )
    public void consume(ModelDeleteEvent event) {
        service.deleteAllByModelId(event.getModelId());

        LOGGER.info("Model deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "model-update"
    )
    public void consume(ModelUpdateEvent event) {
        service.getByModelId(event.getId()).forEach(filter -> {
            filter.setModelName(event.getName());
            filter.setBrandId(event.getBrandId());
            filter.setBrandName(service.getByBrandId(event.getBrandId()).get(0).getBrandName());
            service.save(filter);
        });

        LOGGER.info("Model updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-create"
    )
    public void consume(CarRentalCreateEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState(CarState.RENTED);
        service.save(filter);

        LOGGER.info("Car rental created event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-update"
    )
    public void consume(CarRentalUpdateEvent event) {
        Filter oldCar = service.getByCarId(event.getOldCarId());
        Filter newCar = service.getByCarId(event.getNewCarId());
        oldCar.setState(CarState.AVAILABLE); // 1 = Available
        newCar.setState(CarState.RENTED); // 3 = Rented
        service.save(oldCar);
        service.save(newCar);

        LOGGER.info("Car rental updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-delete"
    )
    public void consume(CarRentalDeleteEvent event) {
        Filter car = service.getByCarId(event.getCarId());
        car.setState(CarState.AVAILABLE);
        service.save(car);
        LOGGER.info("Car rental deleted event consumed: {}", event);
    }
}
