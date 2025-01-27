package com.rental.car_rental.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;
    private String brand;

    @Lob
    private byte[] image;

    private boolean available;
}