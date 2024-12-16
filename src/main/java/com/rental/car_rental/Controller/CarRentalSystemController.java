package com.rental.car_rental.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarRentalSystemController {

    @GetMapping("/main_page")
    public String mainPage(Model model, HttpSession session, RedirectAttributes redirectAttributes){
        return "main_page";
    }
}
