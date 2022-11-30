package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateBrandResponse;
import com.kodlamaio.inventoryservice.dataAccess.BrandRepository;
import com.kodlamaio.inventoryservice.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    BrandRepository brandRepository;
    ModelMapperService mapper;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands=brandRepository.findAll();
        List<GetAllBrandsResponse> responses=brands.stream()
                .map(brand-> mapper.forResponse()
                        .map(brand,GetAllBrandsResponse.class)).toList();


        return responses;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        checkIfBrandExistsByName(createBrandRequest.getName());
        Brand brand = mapper.forRequest().map(createBrandRequest, Brand.class);
        brand.setId(UUID.randomUUID().toString());
        brandRepository.save(brand);

        CreateBrandResponse createBrandResponse = mapper.forResponse().map(brand, CreateBrandResponse.class);
        return createBrandResponse;
    }

    @Override
    public UpdateBrandResponse update(String id,UpdateBrandRequest updateBrandRequest) {
        Brand brand=mapper.forRequest().map(updateBrandRequest,Brand.class);
        brand.setId(id);
        brandRepository.save(brand);

        UpdateBrandResponse response=mapper.forResponse().map(brand,UpdateBrandResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        brandRepository.deleteById(id);

    }

    private void checkIfBrandExistsByName(String name) {
        Brand currentBrand = brandRepository.findByName(name);
        if(currentBrand!=null) {
            throw new BusinessException("BRAND.EXISTS!");
        }
    }
}
