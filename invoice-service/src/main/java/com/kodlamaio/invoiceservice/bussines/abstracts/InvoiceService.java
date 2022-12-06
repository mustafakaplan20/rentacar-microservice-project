package com.kodlamaio.invoiceservice.bussines.abstracts;

import com.kodlamaio.invoiceservice.bussines.requests.update.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.bussines.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetAllInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.update.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.requests.create.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoiceResponse> getAll();
    GetInvoiceResponse getById(String id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id);
    void delete(String id);
    void createInvoice(Invoice invoice);
}