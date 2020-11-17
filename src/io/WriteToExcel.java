package io;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToExcel {
    public void write(ArrayList<ArrayList<String>> data, File file, String sheetName) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        int rowCount = 0;

        for (ArrayList<String> row : data) {
            Row rowExcel = sheet.createRow(rowCount++);
            int cellCount = 0;
            for (String cell : row) {
                Cell cellExcel = rowExcel.createCell(cellCount++);
                cellExcel.setCellValue(cell);
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
