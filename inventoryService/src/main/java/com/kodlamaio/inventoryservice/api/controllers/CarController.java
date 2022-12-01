package com.kodlamaio.inventoryservice.api.controllers;

import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    CarService carService;

    @GetMapping
    List<GetAllCarsResponse> getAll() {
        return carService.getAll();
    }
    @PostMapping
    CreateCarResponse add(@RequestBody CreateCarRequest createCarRequest) {
        return carService.add(createCarRequest);
    }
    @PutMapping("/{id}")
    UpdateCarResponse update(@PathVariable String id, @RequestBody UpdateCarRequest updateCarRequest) {
        return carService.update(id,updateCarRequest);

    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id){
        carService.delete(id);
    }
}
