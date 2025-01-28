package com.rental.car_rental.Controller;

import com.rental.car_rental.Enum.UserRole;
import com.rental.car_rental.Model.Report;
import com.rental.car_rental.Model.User;
import com.rental.car_rental.Service.ReportService;
import com.rental.car_rental.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @PostMapping("/submitForm")
    public String submitForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("subject", subject);
        model.addAttribute("message", message);

        Report report = new Report();
        report.setName_of_submitter(name);
        report.setEmail(email);
        report.setSubject(subject);
        report.setDescription(message);

        reportService.saveReport(report);

        redirectAttributes.addFlashAttribute("successMessage", "Pomyślnie utworzono zgłoszenie. Odezwiemy się do Ciebie w ciągu kilku dni roboczych.");
        return "redirect:/submitForm/success";
    }

    @GetMapping("/submitForm/success")
    public String success() {
        return "submit_form_success_page";
    }

    @GetMapping("/review_reports")
    public String reviewReports(Model model, HttpSession session) {
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
        }
        else {
            return "redirect:/login";
        }
        List<Report> getAllResolvedReports = reportService.getAllResolvedReports();
        model.addAttribute("allResolvedReports", getAllResolvedReports);
        List<Report> getAllUnresolvedReports = reportService.getAllUnresolvedReports();
        model.addAttribute("allUnresolvedReports", getAllUnresolvedReports);
        return "reports_page";
    }

    @PostMapping("/resolve-report/{id}")
    @ResponseBody
    public void resolveReport(@PathVariable Long id) {
        Report report = reportService.findById(id);
        if (report != null) {
            report.setIsResolved(true);
            reportService.save(report);
        }
    }

    @PostMapping("/unresolve-report/{id}")
    @ResponseBody
    public void unresolveReport(@PathVariable Long id) {
        Report report = reportService.findById(id);
        if (report != null) {
            report.setIsResolved(false);
            reportService.save(report);
        }
    }
}
