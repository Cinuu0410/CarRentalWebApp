package com.rental.car_rental.Repository;
import com.rental.car_rental.Model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Cars, Long> {
    List<Cars> findByAvailableTrue();

    List<Cars> findAll();

    Optional<Cars> findByNumber(Long number);
}
