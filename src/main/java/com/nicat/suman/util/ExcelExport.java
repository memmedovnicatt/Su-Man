package com.nicat.suman.util;

import com.nicat.suman.dao.entity.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ExcelExport {

    public void createHeaderRow(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        HSSFRow row = sheet.createRow(0);
        String[] headers = {"Id", "Name", "Surname", "Address", "Phone Number", "Currency", "Price"};
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    public void createDataRows(HSSFWorkbook workbook, HSSFSheet sheet, List<Customer> customers) {
        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        int dataRowIndex = 1;
        for (Customer customer : customers) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex++);
            dataRow.createCell(0).setCellValue(customer.getId());
            dataRow.createCell(1).setCellValue(customer.getName());
            dataRow.createCell(2).setCellValue(customer.getSurname());
            dataRow.createCell(3).setCellValue(customer.getAddress());
            dataRow.createCell(4).setCellValue(customer.getPhoneNumber());
            dataRow.createCell(5).setCellValue(customer.getCurrency());
            dataRow.createCell(6).setCellValue(customer.getPrice());

            for (int i = 0; i <= 6; i++) {
                dataRow.getCell(i).setCellStyle(dataStyle);
            }
        }
    }

    public void autoSizeColumns(HSSFSheet sheet) {
        int numberOfColumns = 7;
        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}