package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(String id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(UpdateBrandRequest request, String id);
    void delete(String id);

}
