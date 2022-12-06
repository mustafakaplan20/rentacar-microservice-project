package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(String id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(UpdateModelRequest request, String id);
    void delete(String id);
}
