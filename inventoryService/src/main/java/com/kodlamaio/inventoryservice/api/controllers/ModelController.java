package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateModelResponse add(@Valid @RequestBody CreateModelRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@Valid @RequestBody UpdateModelRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable String id) {
        return service.getById(id);
    }
}
