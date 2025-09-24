package com.example.salarytracker.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelParserUtils {
    public static String getString(Row r, int idx) {
        Cell c = r.getCell(idx);
        if (c == null) return null;
        return switch (c.getCellType()) {
            case STRING -> c.getStringCellValue();
            case NUMERIC -> String.valueOf(c.getNumericCellValue());
            case BOOLEAN -> String.valueOf(c.getBooleanCellValue());
            default -> null;
        };
    }

    public static Double getDouble(Row r, int idx) {
        Cell c = r.getCell(idx);
        if (c == null) return null;
        return switch (c.getCellType()) {
            case NUMERIC -> c.getNumericCellValue();
            case STRING -> {
                String s = c.getStringCellValue();
                try { yield Double.parseDouble(s); } catch (Exception e) { yield null; }
            }
            default -> null;
        };
    }
}
