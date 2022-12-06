package com.kodlamaio.filterservice.business.abstracts;

import com.kodlamaio.filterservice.business.responses.GetAllFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;

import java.util.List;

public interface FilterService {
    List<GetAllFilterResponse> getAll();
    List<GetAllFilterResponse> getByBrandName(String brandName);
    List<GetAllFilterResponse> getByModelName(String modelName);
    List<GetAllFilterResponse> getByPlate(String plate);
    List<GetAllFilterResponse> searchByPlate(String plate);
    List<GetAllFilterResponse> searchByBrandName(String brandName);
    List<GetAllFilterResponse> searchByModelName(String modelName);
    List<GetAllFilterResponse> getByModelYear(int modelYear);
    List<GetAllFilterResponse> getByState(int state);

    // Consumer service
    Filter getByCarId(String id);
    List<Filter> getByModelId(String modelId);
    List<Filter> getByBrandId(String brandId);
    void save(Filter mongodb);
    void delete(String id);
    void deleteAllByBrandId(String brandId);
    void deleteAllByModelId(String modelId);
}
