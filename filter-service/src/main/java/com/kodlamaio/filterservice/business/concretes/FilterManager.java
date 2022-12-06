package com.kodlamaio.filterservice.business.concretes;

import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;
import com.kodlamaio.filterservice.dataAccess.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFilterResponse> getAll() {
        List<Filter> filters = repository.findAll();
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> getByBrandName(String brandName) {
        List<Filter> filters = repository.findByBrandNameIgnoreCase(brandName);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> getByModelName(String modelName) {
        List<Filter> filters = repository.findByModelNameIgnoreCase(modelName);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> getByPlate(String plate) {
        List<Filter> filters = repository.findByPlateIgnoreCase(plate);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> searchByPlate(String plate) {
        List<Filter> filters = repository.findByPlateContainingIgnoreCase(plate);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> searchByBrandName(String brandName) {
        List<Filter> filters = repository.findByBrandNameContainingIgnoreCase(brandName);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> searchByModelName(String modelName) {
        List<Filter> filters = repository.findByModelNameContainingIgnoreCase(modelName);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> getByModelYear(int modelYear) {
        List<Filter> filters = repository.findByModelYear(modelYear);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFilterResponse> getByState(int state) {
        List<Filter> filters = repository.findByState(state);
        List<GetAllFilterResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFilterResponse.class))
                .toList();

        return response;
    }

    @Override
    public Filter getByCarId(String id) {
        return repository.findByCarId(id);
    }

    @Override
    public List<Filter> getByModelId(String modelId) {
        return repository.findByModelId(modelId);
    }

    @Override
    public List<Filter> getByBrandId(String brandId) {
        return repository.findByBrandId(brandId);
    }

    @Override
    public void save(Filter filter) {
        repository.save(filter);
    }

    @Override
    public void delete(String id) {
        repository.deleteByCarId(id);
    }

    @Override
    public void deleteAllByBrandId(String brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteAllByModelId(String modelId) {
        repository.deleteAllByModelId(modelId);
    }
}
