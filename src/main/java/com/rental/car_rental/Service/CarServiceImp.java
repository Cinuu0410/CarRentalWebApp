package com.rental.car_rental.Service;
import com.rental.car_rental.Model.Cars;
import com.rental.car_rental.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    private final CarRepository carRepository;

    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void saveCar(Cars cars) {
        carRepository.save(cars);
    }

    @Override
    public void deleteCar(Long carId) {

    }
    @Override
    public List<Cars> getAllCars() {
        return carRepository.findAll();
    }
}
