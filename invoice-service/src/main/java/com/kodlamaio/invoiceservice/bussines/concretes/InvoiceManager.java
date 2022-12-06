package com.kodlamaio.invoiceservice.bussines.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.invoiceservice.bussines.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.bussines.requests.create.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.bussines.requests.update.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.bussines.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetAllInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.update.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.dataAccess.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllInvoiceResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoiceResponse> response = invoices
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoiceResponse.class)
                    ).toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(UUID.randomUUID().toString());
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.forResponse().map(invoice, CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(UpdateInvoiceRequest request, String id) {
        checkIfInvoiceExists(id);
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.forResponse().map(invoice, UpdateInvoiceResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    @Override
    public void createInvoice(Invoice invoice) {
        invoice.setId(UUID.randomUUID().toString());
        repository.save(invoice);
    }

    private void checkIfInvoiceExists(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("INVOICE_NOT_FOUND");
        }
    }
}