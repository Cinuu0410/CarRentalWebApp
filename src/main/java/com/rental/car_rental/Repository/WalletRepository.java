package com.rental.car_rental.Repository;

import com.rental.car_rental.Model.User;
import com.rental.car_rental.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByUser(User user);
}
