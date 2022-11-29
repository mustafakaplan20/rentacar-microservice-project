package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelController {
    ModelService modelService;

    @GetMapping("/getAll")
    List<GetAllModelsResponse> getAll() {
        return modelService.getAll();
    }
    @PostMapping("/add")
    CreateModelResponse add(@RequestBody CreateModelRequest createModelRequest) {
        return modelService.add(createModelRequest);
    }
    @PutMapping("/update/{id}")
    UpdateModelResponse update(@PathVariable String id, @RequestBody UpdateModelRequest updateModelRequest) {
        return modelService.update(id,updateModelRequest);

    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable String id){
        modelService.delete(id);
    }
}
