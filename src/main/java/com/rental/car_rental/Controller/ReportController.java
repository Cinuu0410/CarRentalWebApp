package com.rental.car_rental.Controller;

import com.rental.car_rental.Model.Report;
import com.rental.car_rental.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

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
}
