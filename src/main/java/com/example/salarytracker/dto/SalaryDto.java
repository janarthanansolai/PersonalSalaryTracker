package com.example.salarytracker.dto;

public class SalaryDto {
    public String yearLabel;
    public String company;
    public String currency;
    public Double fixedCtc;
    public Double variable;
    public Double deductions;
    public Double fullCtc;
    public Double convertedFullCtc; // in requested base currency
}