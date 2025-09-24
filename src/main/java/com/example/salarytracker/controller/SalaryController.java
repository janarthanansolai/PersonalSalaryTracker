package com.example.salarytracker.controller;

import com.example.salarytracker.dto.SalaryDto;
import com.example.salarytracker.dto.TrendPoint;
import com.example.salarytracker.entity.AppUser;
import com.example.salarytracker.entity.SalaryRecord;
import com.example.salarytracker.repository.AppUserRepository;
import com.example.salarytracker.service.ExcelParseService;
import com.example.salarytracker.service.SalaryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {
    private final ExcelParseService excelParseService;
    private final AppUserRepository userRepo;
    private final SalaryService salaryService;

    public SalaryController(ExcelParseService excelParseService, AppUserRepository userRepo, SalaryService salaryService) {
        this.excelParseService = excelParseService;
        this.userRepo = userRepo;
        this.salaryService = salaryService;
    }

    private AppUser getOrCreateUser(OidcUser oidcUser) {
        String email = oidcUser.getEmail();
        return userRepo.findByEmail(email).orElseGet(() -> {
            AppUser u = new AppUser();
            u.setEmail(email);
            u.setName(oidcUser.getFullName());
            u.setRoles(Set.of("USER"));
            return userRepo.save(u);
        });
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal OidcUser oidcUser) throws Exception {
        AppUser user = getOrCreateUser(oidcUser);
        excelParseService.parseAndSave(file, user);
        return ResponseEntity.ok(Map.of("status", "uploaded"));
    }

    @GetMapping("/{year}")
    public List<SalaryDto> getByYear(@PathVariable String year, @RequestParam(defaultValue = "USD") String baseCurrency, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        return salaryService.getByYear(user, year, baseCurrency);
    }

    @GetMapping("/trend")
    public List<TrendPoint> trend(@RequestParam(defaultValue = "USD") String baseCurrency, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        return salaryService.trend(user, baseCurrency);
    }

    @GetMapping("/latest")
    public SalaryDto latest(@RequestParam(defaultValue = "USD") String baseCurrency, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        return salaryService.latest(user, baseCurrency);
    }

    @PutMapping("/{year}/hike")
    public ResponseEntity<?> hike(@PathVariable String year, @RequestParam double percent, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        SalaryRecord updated = salaryService.applyHike(user, year, percent);
        return ResponseEntity.ok(Map.of("updatedId", updated.getId()));
    }

    @PostMapping("/switch")
    public ResponseEntity<?> switchCompany(@RequestBody SalaryRecord r, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        SalaryRecord saved = salaryService.switchCompany(user, r);
        return ResponseEntity.ok(Map.of("id", saved.getId()));
    }

    @GetMapping("/compare")
    public Map<String, Double> compare(@RequestParam(defaultValue = "USD") String baseCurrency, @AuthenticationPrincipal OidcUser oidcUser) {
        AppUser user = getOrCreateUser(oidcUser);
        return salaryService.compareByCompany(user, baseCurrency);
    }
}
