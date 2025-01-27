package com.rental.car_rental.Service;

import com.rental.car_rental.Model.Car;
import com.rental.car_rental.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void saveCar(Long number, String brand, Boolean available, MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();

        Car car = new Car();
        car.setNumber(number);
        car.setBrand(brand);
        car.setImage(imageBytes);
        car.setAvailable(available);

        carRepository.save(car);
    }

    public void editCar(Long carId, Long number, String brand, Boolean available,
                        MultipartFile image) throws IOException {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setNumber(number);
            car.setBrand(brand);
            car.setAvailable(available);

            if (image != null && !image.isEmpty()) {
                byte[] imageData = image.getBytes();
                car.setImage(imageData);
            }

            carRepository.save(car);
        } else {
            throw new IllegalArgumentException("Samochód o podanym ID nie istnieje");
        }
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);

    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByAvailableTrue();
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }
}
