package com.rental.car_rental.Service;

import com.rental.car_rental.Model.User;
import com.rental.car_rental.Model.Wallet;
import com.rental.car_rental.Repository.UserRepository;
import com.rental.car_rental.Repository.WalletRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;


    public BigDecimal getBalance(Long userId) {
        return userRepository.findWalletBalanceByUserId(userId);
    }


    public void addToBalance(User user, BigDecimal amount) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            existingUser.setWallet(existingUser.getWallet() != null ? existingUser.getWallet() : BigDecimal.ZERO);
            existingUser.setWallet(existingUser.getWallet().add(amount));
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    // Odejmowanie środków z portfela użytkownika
    public void deductFromBalance(User user, BigDecimal amount) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BigDecimal currentBalance = existingUser.getWallet() != null ? existingUser.getWallet() : BigDecimal.ZERO;
        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds in the wallet.");
        }

        existingUser.setWallet(currentBalance.subtract(amount));
        userRepository.save(existingUser);
    }
}
