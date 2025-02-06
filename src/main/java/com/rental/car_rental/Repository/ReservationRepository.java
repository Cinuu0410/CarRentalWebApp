package com.rental.car_rental.Repository;

import com.rental.car_rental.Model.Reservation;
import com.rental.car_rental.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserAndStatus(User user, String active);
}
