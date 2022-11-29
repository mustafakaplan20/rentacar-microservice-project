package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {
    BrandService brandService;

    @GetMapping("/getAll")
    List<GetAllBrandsResponse> getAll() {
        return brandService.getAll();
    }
    @PostMapping("/add")
    CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest) {
        return brandService.add(createBrandRequest);
    }
    @PutMapping("/update/{id}")
    UpdateBrandResponse update(@PathVariable String id, @RequestBody UpdateBrandRequest updateBrandRequest) {
        return brandService.update(id,updateBrandRequest);

    }
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable String id){
        brandService.delete(id);
    }


}
