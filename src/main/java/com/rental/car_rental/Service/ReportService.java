package com.rental.car_rental.Service;

import com.rental.car_rental.Model.Report;
import com.rental.car_rental.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void saveReport(Report report) {
        report.setIsResolved("false");
        reportRepository.save(report);
    }
}
