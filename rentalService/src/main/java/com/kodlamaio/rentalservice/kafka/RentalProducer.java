package com.kodlamaio.rentalservice.kafka;

import com.kodlamaio.common.events.payments.PaymentReceiveEvent;
import com.kodlamaio.common.events.rentals.RentalCreateEvent;
import com.kodlamaio.common.events.rentals.RentalDeleteEvent;
import com.kodlamaio.common.events.rentals.RentalUpdateEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RentalProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalProducer.class);

    private final NewTopic topic;

    private final KafkaTemplate<String, RentalCreateEvent> kafkaTemplate;

    public RentalProducer(NewTopic topic, KafkaTemplate<String, RentalCreateEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RentalCreateEvent rentalCreateEvent) {
        LOGGER.info(String.format("Rental created event => %s", rentalCreateEvent.toString()));

        Message<RentalCreateEvent> message = MessageBuilder
                .withPayload(rentalCreateEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(RentalUpdateEvent rentalUpdatedEvent) {
        LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));

        Message<RentalUpdateEvent> message = MessageBuilder
                .withPayload(rentalUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(RentalDeleteEvent rentalDeleteEvent) {
        LOGGER.info(String.format("Rental deleted event => %s", rentalDeleteEvent.toString()));

        Message<RentalDeleteEvent> message = MessageBuilder
                .withPayload(rentalDeleteEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(PaymentReceiveEvent event) {
        LOGGER.info(String.format("Payment received event => %s", event.toString()));

        Message<PaymentReceiveEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();

        kafkaTemplate.send(message);
    }
}
