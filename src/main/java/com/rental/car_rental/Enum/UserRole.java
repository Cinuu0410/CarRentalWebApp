package com.rental.car_rental.Enum;

public enum UserRole {
    ADMINISTRATOR("Administrator"),
    UZYTKOWNIK("Uzytkownik");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
