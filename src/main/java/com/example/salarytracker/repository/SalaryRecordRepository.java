package com.example.salarytracker.repository;

import com.example.salarytracker.entity.SalaryRecord;
import com.example.salarytracker.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface SalaryRecordRepository extends JpaRepository<SalaryRecord, Long> {
    List<SalaryRecord> findByUserOrderByYearLabelAsc(AppUser user);
    List<SalaryRecord> findByUser(AppUser user);
    Optional<SalaryRecord> findFirstByUserOrderByYearLabelDesc(AppUser user);
    List<SalaryRecord> findByUserAndYearLabel(AppUser user, String yearLabel);
}