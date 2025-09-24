package com.example.salarytracker.service;

import org.springframework.stereotype.Service;


import java.util.Map;


@Service
public class CurrencyConversionService {
    // Simple in-memory rates relative to USD — replace with real API for production
    private final Map<String, Double> ratesToUSD = Map.of(
            "USD", 1.0,
            "INR", 0.012, // 1 INR = 0.012 USD (example)
            "AED", 0.27
    );


    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) return amount;
        Double fromRate = ratesToUSD.get(fromCurrency.toUpperCase());
        Double toRate = ratesToUSD.get(toCurrency.toUpperCase());
        if (fromRate == null || toRate == null) throw new IllegalArgumentException("Unsupported currency");
        double amountInUsd = amount * fromRate;
        return amountInUsd / toRate;
    }
}