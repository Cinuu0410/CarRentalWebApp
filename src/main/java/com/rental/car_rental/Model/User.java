package com.rental.car_rental.Model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    @Transient
    private String rawPassword;

}
