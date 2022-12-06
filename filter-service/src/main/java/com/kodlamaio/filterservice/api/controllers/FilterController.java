package com.kodlamaio.filterservice.api.controllers;

import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.responses.GetAllFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FilterController {
    private final FilterService service;

    @GetMapping
    public List<GetAllFilterResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/brand")
    public List<GetAllFilterResponse> getByBrandName(@RequestParam String brandName) {
        return service.getByBrandName(brandName);
    }

    @GetMapping("/model")
    public List<GetAllFilterResponse> getByModelName(@RequestParam String modelName) {
        return service.getByModelName(modelName);
    }

    @GetMapping("/plate")
    public List<GetAllFilterResponse> getByPlate(@RequestParam String plate) {
        return service.getByPlate(plate);
    }

    @GetMapping("/plate-search")
    public List<GetAllFilterResponse> searchByPlate(@RequestParam String plate) {
        return service.searchByPlate(plate);
    }

    @GetMapping("/brand-search")
    public List<GetAllFilterResponse> searchByBrandName(@RequestParam String brandName) {
        return service.searchByBrandName(brandName);
    }

    @GetMapping("/model-search")
    public List<GetAllFilterResponse> searchByModelName(@RequestParam String modelName) {
        return service.searchByModelName(modelName);
    }

    @GetMapping("/year")
    public List<GetAllFilterResponse> getByModelYear(@RequestParam int modelYear) {
        return service.getByModelYear(modelYear);
    }

    @GetMapping("/state")
    public List<GetAllFilterResponse> getByState(@RequestParam int state) {
        return service.getByState(state);
    }
}
