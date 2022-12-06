package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.common.events.inventories.models.ModelDeleteEvent;
import com.kodlamaio.common.events.inventories.models.ModelUpdateEvent;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryservice.dataAccess.ModelRepository;
import com.kodlamaio.inventoryservice.entities.Model;
import com.kodlamaio.inventoryservice.kafka.InventoryProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapperService mapper;
    private final InventoryProducer producer;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> response = models
                .stream()
                .map(model -> mapper.forResponse().map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(String id) {
        checkIfExistsById(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.forResponse().map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        checkIfExistsByName(request.getName());
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(UUID.randomUUID().toString());
        repository.save(model);
        CreateModelResponse response = mapper.forResponse().map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(UpdateModelRequest request, String id) {
        checkIfExistsById(id);
        checkIfExistsByName(request.getName());
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(id);
        repository.save(model);
        UpdateModelResponse response = mapper.forResponse().map(model, UpdateModelResponse.class);
        updateInMongoDB(id, request.getName(), request.getBrandId());

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfExistsById(id);
        repository.deleteById(id);
        deleteInMongoDB(id);
    }

    private void checkIfExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("MODEL.NOT.EXISTS");
        }
    }

    private void checkIfExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("MODEL.EXISTS");
        }
    }

    private void updateInMongoDB(String id, String name, String brandId) {
        ModelUpdateEvent event = new ModelUpdateEvent();
        event.setId(id);
        event.setName(name);
        event.setBrandId(brandId);
        producer.sendMessage(event);
    }

    private void deleteInMongoDB(String id) {
        ModelDeleteEvent event = new ModelDeleteEvent();
        event.setModelId(id);
        producer.sendMessage(event);
    }
}