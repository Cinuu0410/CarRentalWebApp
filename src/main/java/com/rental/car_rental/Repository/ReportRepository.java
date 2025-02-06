package com.rental.car_rental.Repository;

import com.rental.car_rental.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.isResolved = true")
    List<Report> findByIsResolvedTrue();

    @Query("SELECT r FROM Report r WHERE r.isResolved = false ")
    List<Report> findByIsResolvedFalse();
}
