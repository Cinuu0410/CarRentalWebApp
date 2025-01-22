package com.rental.car_rental.Controller;

import com.rental.car_rental.Enum.UserRole;
import com.rental.car_rental.Model.Cars;
import com.rental.car_rental.Model.User;
import com.rental.car_rental.Service.CarService;
import com.rental.car_rental.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
@Data
public class CarRentalSystemController {

    @Autowired
    private final UserService userService;
    private final CarService carService;



    @GetMapping("/main_page")
    public String mainPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);

            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
        }
        return "main_page";
    }

    @GetMapping("/contact")
    public String contact(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
        }
        return "contact_page";
    }

    @GetMapping("/about_us")
    public String aboutUsPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
        }
        return "about_us_page";
    }

    @GetMapping("/panel")
    public String panelPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);

            if (!loggedRole.equals(UserRole.ADMINISTRATOR.getRoleName())) {
                // Użytkownik nie ma roli SUPER_USER, przekieruj na stronę główną
                return "redirect:/main_page";
            }
            // Zapisz informacje o zalogowanym użytkowniku i roli w sesji
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            List<Cars> getAllCars = carService.getAllCars();
            model.addAttribute("getAllCars", getAllCars);

        }
        else {
            return "redirect:/login";
        }
        return "panel";
    }
    @GetMapping("/rent")
    public String rentPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Pobierz informacje o zalogowanym użytkowniku

            // Dodaj informacje o zalogowanym użytkowniku do modelu
            model.addAttribute("loggedInUser", loggedInUser);




        }

//        // Pobierz listę dostępnych rowerów z serwisu
        List<Cars> availableCars = carService.getAvailableCars();
        model.addAttribute("availableCars", availableCars);

        List<Cars> getAllCars = carService.getAllCars();
        model.addAttribute("getAllCars", getAllCars);

        return "rent_page";
    }

    @PostMapping("/addCar")
    public String addCars(@RequestParam("carNumber") String carNumber,
                          @RequestParam("carBrand") String carBrand,
                          @RequestParam("carAvailable") boolean carAvailable) {
        Cars newCar = new Cars();
        newCar.setNumber(Long.valueOf(carNumber));
        newCar.setBrand(carBrand);
        newCar.setAvailable(carAvailable);

        carService.saveCar(newCar);

        return "redirect:/panel"; // Lub inna strona docelowa po dodaniu roweru
    }
    @PostMapping("/deleteCars/{CarId}")
    public String deleteBike(@PathVariable Long carId) {
        carService.deleteCar(carId);

        return "redirect:/panel"; // Lub inna strona docelowa po usunięciu roweru
    }


}