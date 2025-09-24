package com.example.salarytracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "salary_record")
public class SalaryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private AppUser user;

    private String yearLabel;
    private String company;
    private String currency;

    private Double fixedCtc;
    private Double variable;
    private Double deductions;

    private LocalDateTime createdAt;


    public SalaryRecord() { this.createdAt = LocalDateTime.now(); }


    // convenience
    public Double getFullCtc() {
        double f = fixedCtc == null ? 0 : fixedCtc;
        double v = variable == null ? 0 : variable;
        double d = deductions == null ? 0 : deductions;
        return f + v - d;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
    public String getYearLabel() { return yearLabel; }
    public void setYearLabel(String yearLabel) { this.yearLabel = yearLabel; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Double getFixedCtc() { return fixedCtc; }
    public void setFixedCtc(Double fixedCtc) { this.fixedCtc = fixedCtc; }
    public Double getVariable() { return variable; }
    public void setVariable(Double variable) { this.variable = variable; }
    public Double getDeductions() { return deductions; }
    public void setDeductions(Double deductions) { this.deductions = deductions; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}