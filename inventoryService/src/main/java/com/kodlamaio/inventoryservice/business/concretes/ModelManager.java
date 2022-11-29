package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateModelResponse;
import com.kodlamaio.inventoryservice.dataAccess.ModelRepository;
import com.kodlamaio.inventoryservice.entities.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    ModelRepository modelRepository;
    ModelMapperService mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models=modelRepository.findAll();
        List<GetAllModelsResponse> responses=models.stream()
                .map(model-> mapper.forResponse()
                        .map(model,GetAllModelsResponse.class)).toList();

        return responses;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        checkIfModelExistsByName(createModelRequest.getName());
        Model model = mapper.forRequest().map(createModelRequest, Model.class);
        modelRepository.save(model);

        CreateModelResponse response= mapper.forResponse().map(model,CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(String id,UpdateModelRequest updateModelRequest) {
        Model model=mapper.forRequest().map(updateModelRequest,Model.class);
        model.setId(id);
        modelRepository.save(model);

        UpdateModelResponse response=mapper.forResponse().map(model,UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        modelRepository.deleteById(id);

    }

    private void checkIfModelExistsByName(String name) {
        Model model = modelRepository.findByName(name);
        if(model!=null) {
            throw new BusinessException("MODEL.EXISTS!");
        }
    }
}
