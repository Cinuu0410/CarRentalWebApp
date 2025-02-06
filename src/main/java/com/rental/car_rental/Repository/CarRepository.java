package com.rental.car_rental.Repository;
import com.rental.car_rental.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByAvailableTrue();
}
