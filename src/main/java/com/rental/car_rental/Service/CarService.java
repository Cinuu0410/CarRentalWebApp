package com.rental.car_rental.Service;

import com.rental.car_rental.Model.Cars;

import java.util.List;

public interface CarService {
    void saveCar(Cars car);

    void deleteCar(Long carId);
    List<Cars> getAllCars();
}
