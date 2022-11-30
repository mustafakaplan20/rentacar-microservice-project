package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.requests.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.requests.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.responses.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.responses.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.responses.update.UpdateCarResponse;
import com.kodlamaio.inventoryservice.dataAccess.CarRepository;
import com.kodlamaio.inventoryservice.entities.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    CarRepository carRepository;
    ModelMapperService mapper;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars=carRepository.findAll();
        List<GetAllCarsResponse> responses=cars.stream()
                .map(car-> mapper.forResponse()
                        .map(car,GetAllCarsResponse.class)).toList();


        return responses;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {
        checkIfCarExistsByPlate(createCarRequest.getPlate());
        Car car = mapper.forRequest().map(createCarRequest, Car.class);
        car.setId(UUID.randomUUID().toString());
        carRepository.save(car);

        CreateCarResponse response= mapper.forResponse().map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(String id,UpdateCarRequest updateCarRequest) {
        Car car=mapper.forRequest().map(updateCarRequest,Car.class);
        car.setId(id);
        carRepository.save(car);

        UpdateCarResponse response=mapper.forResponse().map(car,UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        carRepository.deleteById(id);
    }
    private void checkIfCarExistsByPlate(String plate){
        Car car=carRepository.findByPlate(plate);

        if(car!=null) {
            throw new BusinessException("CAR.EXISTS!");
        }
    }

}
