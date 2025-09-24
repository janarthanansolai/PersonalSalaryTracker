package com.example.salarytracker.service;

import com.example.salarytracker.entity.AppUser;
import com.example.salarytracker.entity.SalaryRecord;
import com.example.salarytracker.repository.SalaryRecordRepository;
import com.example.salarytracker.util.ExcelParserUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;

@Service
public class ExcelParseService {
    private final SalaryRecordRepository repo;

    public ExcelParseService(SalaryRecordRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void parseAndSave(MultipartFile file, AppUser user) throws Exception {
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                String sheetName = sheet.getSheetName(); // expected year label
                Iterator<Row> rows = sheet.rowIterator();
                // assume first row is header; parse rows thereafter
                if (!rows.hasNext()) continue;
                Row header = rows.next();
                while (rows.hasNext()) {
                    Row r = rows.next();
                    // expected columns: company, currency, fixedCtc, variable, deductions
                    SalaryRecord rec = new SalaryRecord();
                    rec.setUser(user);
                    rec.setYearLabel(sheetName);
                    rec.setCompany(ExcelParserUtils.getString(r, 0));
                    rec.setCurrency(ExcelParserUtils.getString(r, 1));
                    rec.setFixedCtc(ExcelParserUtils.getDouble(r, 2));
                    rec.setVariable(ExcelParserUtils.getDouble(r, 3));
                    rec.setDeductions(ExcelParserUtils.getDouble(r, 4));
                    repo.save(rec);
                }
            }
        }
    }
}
