package com.kodlamaio.invoiceservice.kafka;

import com.kodlamaio.common.events.payments.PaymentReceiveEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceservice.bussines.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.clients.CarClient;
import com.kodlamaio.invoiceservice.entities.Invoice;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalConsumer.class);
    private final InvoiceService service;
    private final ModelMapperService mapper;
    private final CarClient client;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "payment-received"
    )
    public void consume(PaymentReceiveEvent event) {
        Invoice invoice = mapper.forRequest().map(event, Invoice.class);
        invoice.setDailyPrice(event.getDailyPrice());
        invoice.setTotalPrice(event.getTotalPrice());

        invoice.setBrandName(client.getCarResponse(event.getCarId()).getBrandName());
        invoice.setModelName(client.getCarResponse(event.getCarId()).getModelName());
        invoice.setModelYear(client.getCarResponse(event.getCarId()).getModelYear());
        service.createInvoice(invoice);
        LOGGER.info("Invoice created for : {}", event.getFullName());
    }
}