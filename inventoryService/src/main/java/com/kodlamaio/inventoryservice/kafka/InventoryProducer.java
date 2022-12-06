package com.kodlamaio.inventoryservice.kafka;


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
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryProducer.class);

    private final NewTopic topic;

    private final KafkaTemplate<String, InventoryCreateEvent> kafkaTemplate;

    public InventoryProducer(NewTopic topic, KafkaTemplate<String, InventoryCreateEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(InventoryCreateEvent event) {
        LOGGER.info(String.format("Inventory created event => %s", event.toString()));

        Message<InventoryCreateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarUpdateEvent event) {
        LOGGER.info(String.format("Car updated event => %s", event.toString()));

        Message<CarUpdateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarDeleteEvent event) {
        LOGGER.info(String.format("Car deleted event => %s", event.toString()));

        Message<CarDeleteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(BrandUpdateEvent event) {
        LOGGER.info(String.format("Brand updated event => %s", event.toString()));

        Message<BrandUpdateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(BrandDeleteEvent event) {
        LOGGER.info(String.format("Brand deleted event => %s", event.toString()));

        Message<BrandDeleteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ModelUpdateEvent event) {
        LOGGER.info(String.format("Model updated event => %s", event.toString()));

        Message<ModelUpdateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ModelDeleteEvent event) {
        LOGGER.info(String.format("Model deleted event => %s", event.toString()));

        Message<ModelDeleteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarRentalCreateEvent event) {
        LOGGER.info(String.format("Car Rental created event => %s", event.toString()));

        Message<CarRentalCreateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarRentalUpdateEvent event) {
        LOGGER.info(String.format("Car rental updated event => %s", event.toString()));

        Message<CarRentalUpdateEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarRentalDeleteEvent event) {
        LOGGER.info(String.format("Car rental deleted event => %s", event.toString()));

        Message<CarRentalDeleteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }
}
