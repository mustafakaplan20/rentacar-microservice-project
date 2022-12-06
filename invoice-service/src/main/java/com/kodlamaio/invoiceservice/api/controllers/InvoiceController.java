package com.kodlamaio.invoiceservice.api.controllers;

import com.kodlamaio.invoiceservice.bussines.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetAllInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.get.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.responses.update.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.bussines.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.bussines.requests.create.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.bussines.requests.update.UpdateInvoiceRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public List<GetAllInvoiceResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable String id) {
        return invoiceService.getById(id);
    }

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest request) {
        return invoiceService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@Valid @RequestBody UpdateInvoiceRequest request, @PathVariable String id) {
        return invoiceService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        invoiceService.delete(id);
    }
}