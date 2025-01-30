package com.rental.car_rental.Controller;

import com.rental.car_rental.Enum.UserRole;
import com.rental.car_rental.Model.Car;
import com.rental.car_rental.Model.User;
import com.rental.car_rental.Service.CarService;
import com.rental.car_rental.Service.UserService;
import com.rental.car_rental.Service.WalletService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
@Data
public class CarRentalSystemController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final CarService carService;

    @Autowired
    private final WalletService walletService;


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
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        }

        return "main_page";
    }

    @GetMapping("/contact")
    public String contactPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
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
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        }
        return "about_us_page";
    }

    @GetMapping("/price_list")
    public String priceListPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        }
        return "price-list_page";
    }

    @GetMapping("/calculate")
    public String calculatePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        }
        return "calculate_page";
    }

    @GetMapping("/panel")
    public String panelPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            if (!loggedRole.equals(UserRole.ADMINISTRATOR.getRoleName())) {
                return "redirect:/main_page";
            }
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }

        return "panel";
    }

    @GetMapping("/edit_cars_page")
    public String editCarsPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            if (!loggedRole.equals(UserRole.ADMINISTRATOR.getRoleName())) {
                return "redirect:/main_page";
            }
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }
        List<Car> getAllCars = carService.getAllCars();
        model.addAttribute("getAllCars", getAllCars);
        return "edit_cars_page";
    }

    @GetMapping("/add_cars_page")
    public String addCarsPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            if (!loggedRole.equals(UserRole.ADMINISTRATOR.getRoleName())) {
                return "redirect:/main_page";
            }
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }
        List<Car> getAllCars = carService.getAllCars();
        model.addAttribute("getAllCars", getAllCars);
        return "add_cars_page";
    }

    @GetMapping("/rent")
    public String rentPage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        }

        List<Car> getAllCars = carService.getAllCars();
        model.addAttribute("getAllCars", getAllCars);

        return "rent_page";
    }

    @GetMapping("/car/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getCarImage(@PathVariable("id") Long carId) {
        Optional<Car> car = carService.findById(carId);

        if (carId == 1) {
            return getLocalImage("car1.jpg");
        } else if (carId == 2) {
            return getLocalImage("car2.jpg");
        } else if (carId == 3) {
            return getLocalImage("car3.jpg");
        }

        if (car.isPresent() && car.get().getImage() != null) {
            byte[] imageBytes = car.get().getImage();

            String contentType = "image/jpeg";

            if (isPng(imageBytes)) {
                contentType = "image/png";
            } else if (isGif(imageBytes)) {
                contentType = "image/gif";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<byte[]> getLocalImage(String filename) {
        try {
            File file = new File("src/main/resources/static/images/" + filename);
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            MediaType mediaType = getMediaTypeForFile(filename);
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private MediaType getMediaTypeForFile(String filename) {
        if (filename.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (filename.endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }


    private boolean isPng(byte[] imageBytes) {
        return imageBytes.length > 8 && imageBytes[0] == (byte) 0x89 && imageBytes[1] == (byte) 0x50
                && imageBytes[2] == (byte) 0x4E && imageBytes[3] == (byte) 0x47;
    }

    private boolean isGif(byte[] imageBytes) {
        return imageBytes.length > 6 && imageBytes[0] == 'G' && imageBytes[1] == 'I' && imageBytes[2] == 'F';
    }

    @PostMapping("/addCar")
    public String addCars(@RequestParam("carNumber") Long carNumber,
                          @RequestParam("carBrand") String carBrand,
                          @RequestParam("carAvailable") boolean carAvailable,
                          @RequestParam("image") MultipartFile image) {
        try {
            carService.saveCar(carNumber, carBrand, carAvailable, image);
            return "redirect:/panel";
        } catch (IOException e) {
            e.printStackTrace();
            return "error_page";
        }
    }

    @GetMapping("/edit-car/{id}")
    public String editCarsPage(@PathVariable("id") Long carId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
        } else {
            return "redirect:/login";
        }

        Optional<Car> car = carService.findById(carId);
        if (car.isPresent()) {
            Car carEntity = car.get();
            model.addAttribute("car", carEntity);
            return "edit_car_page";
        } else {
            return "redirect:/panel?error=CarNotFound";
        }
    }

    @PostMapping("/edit-car")
    public String editEvent(
            @RequestParam("carId") Long carId,
            @RequestParam("number") Long number,
            @RequestParam("brand") String brand,
            @RequestParam("available") Boolean available,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            carService.editCar(carId, number, brand, available, image);
            return "redirect:/panel";
        } catch (IOException e) {
            e.printStackTrace();
            return "error_page";
        }
    }

    @PostMapping("/deleteCars/{CarId}")
    public String deleteBike(@PathVariable("CarId") Long CarId) {
        carService.deleteCar(CarId);

        return "redirect:/panel";
    }

    private void setWalletBalanceInModelAndSession(User loggedInUser, HttpSession session, Model model) {
        BigDecimal walletBalance = walletService.getBalance(loggedInUser.getId());
        if (walletBalance == null) {
            walletBalance = BigDecimal.ZERO;
            session.setAttribute("walletBalance", walletBalance);
        }
        model.addAttribute("walletBalance", walletBalance);
    }

    @GetMapping("/manage_users_funds")
    public String manageUsersFunds(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "manage_users_funds_page";
    }

    @PostMapping("/add_funds_to_user")
    public String addFundsToUser(@RequestParam("username") String username,
                                 @RequestParam("amount") BigDecimal amount,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                try {
                    walletService.addToBalance(user, amount);
                    redirectAttributes.addFlashAttribute("message", "Fundusze zostały dodane pomyślnie!");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("message", "Wystąpił błąd: " + e.getMessage());
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Użytkownik nie został znaleziony.");
            }
        } else {
            return "redirect:/login";
        }
        return "redirect:/manage_users_funds";
    }

    @GetMapping("/reserve_car/{carId}")
    public String showReservationPage(@PathVariable Long carId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }

        Car car = carService.getCarById(carId);
        if (car == null) {
            return "redirect:/error";
        }
        model.addAttribute("car", car);
        model.addAttribute("carBrand", car.getBrand());
        return "reserve_car_page";
    }

    @PostMapping("/confirm_reservation")
    public String confirmReservation(@RequestParam("carId") Long carId, @RequestParam("amount") BigDecimal amount, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getId();
            String loggedRole = userService.getRole(userId);
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("role", loggedRole);
            model.addAttribute("loggedInUser", loggedInUser);
            model.addAttribute("role", loggedRole);
            setWalletBalanceInModelAndSession(loggedInUser, session, model);
        } else {
            return "redirect:/login";
        }
        if(!walletService.checkSufficientFunds(loggedInUser, amount)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Za mało środków na koncie");
            return "redirect:/confirm_reservation/error";
        }
        System.out.println("CarId: " + carId);
        System.out.println("Amount: " + amount);
        carService.setCarUnavailable(carId);
        System.out.println(walletService.checkSufficientFunds(loggedInUser, amount));

        walletService.deductFromBalance(loggedInUser, amount);

        redirectAttributes.addFlashAttribute("successMessage", "Samochód został zarezerwowany");
        return "redirect:/confirm_reservation/success";
    }

    @GetMapping("/confirm_reservation/success")
    public String confirmReservationSuccess() {
        return "confirm_reservation_success_page";
    }
    @GetMapping("/confirm_reservation/error")
    public String confirmReservationError() {
        return "confirm_reservation_error_page";
    }
}