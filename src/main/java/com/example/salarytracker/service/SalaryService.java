package com.example.salarytracker.service;

import com.example.salarytracker.dto.SalaryDto;
import com.example.salarytracker.dto.TrendPoint;
import com.example.salarytracker.entity.AppUser;
import com.example.salarytracker.entity.SalaryRecord;
import com.example.salarytracker.repository.SalaryRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalaryService {
    private final SalaryRecordRepository repo;
    private final CurrencyConversionService conversionService;

    public SalaryService(SalaryRecordRepository repo, CurrencyConversionService conversionService) {
        this.repo = repo;
        this.conversionService = conversionService;
    }

    public List<SalaryDto> getByYear(AppUser user, String year, String baseCurrency) {
        List<SalaryRecord> recs = repo.findByUserAndYearLabel(user, year);
        return recs.stream().map(r -> toDto(r, baseCurrency)).collect(Collectors.toList());
    }

    private SalaryDto toDto(SalaryRecord r, String baseCurrency) {
        SalaryDto d = new SalaryDto();
        d.yearLabel = r.getYearLabel();
        d.company = r.getCompany();
        d.currency = r.getCurrency();
        d.fixedCtc = r.getFixedCtc();
        d.variable = r.getVariable();
        d.deductions = r.getDeductions();
        d.fullCtc = r.getFullCtc();
        d.convertedFullCtc = r.getFullCtc() == null ? null : conversionService.convert(r.getFullCtc(), r.getCurrency(), baseCurrency);
        return d;
    }

    public List<TrendPoint> trend(AppUser user, String baseCurrency) {
        List<SalaryRecord> recs = repo.findByUserOrderByYearLabelAsc(user);
        Map<String, Double> byYear = new LinkedHashMap<>();
        for (SalaryRecord r : recs) {
            double full = r.getFullCtc();
            double converted = conversionService.convert(full, r.getCurrency(), baseCurrency);
            byYear.merge(r.getYearLabel(), converted, Double::sum);
        }
        return byYear.entrySet().stream()
                .map(e -> new TrendPoint(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public SalaryDto latest(AppUser user, String baseCurrency) {
        Optional<SalaryRecord> opt = repo.findFirstByUserOrderByYearLabelDesc(user);
        return opt.map(r -> toDto(r, baseCurrency)).orElse(null);
    }

    @Transactional
    public SalaryRecord applyHike(AppUser user, String year, double percent) {
        List<SalaryRecord> recs = repo.findByUserAndYearLabel(user, year);
        if (recs.isEmpty()) throw new NoSuchElementException("No salary for year");
        // apply hike to all records for that year (simple approach)
        SalaryRecord out = null;
        for (SalaryRecord r : recs) {
            double old = r.getFixedCtc() == null ? 0 : r.getFixedCtc();
            r.setFixedCtc(old * (1 + percent / 100.0));
            repo.save(r);
            out = r;
        }
        return out;
    }

    @Transactional
    public SalaryRecord switchCompany(AppUser user, SalaryRecord newRecord) {
        newRecord.setUser(user);
        return repo.save(newRecord);
    }

    public Map<String, Double> compareByCompany(AppUser user, String baseCurrency) {
        List<SalaryRecord> recs = repo.findByUser(user);
        Map<String, List<SalaryRecord>> grouped = recs.stream().collect(Collectors.groupingBy(SalaryRecord::getCompany));
        Map<String, Double> result = new HashMap<>();
        for (var e : grouped.entrySet()) {
            double avg = e.getValue().stream().mapToDouble(r -> conversionService.convert(r.getFullCtc(), r.getCurrency(), baseCurrency)).average().orElse(0);
            result.put(e.getKey(), avg);
        }
        return result;
    }
}
