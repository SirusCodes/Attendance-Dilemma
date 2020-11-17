package io;

import observableModels.StudentRawObservable;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadStudentDetails {
    public ArrayList<StudentRawObservable> getStudentData(File file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        ArrayList<StudentRawObservable> modelArrayList = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        for (Row row : sheet) {
            StudentRawObservable model = new StudentRawObservable(
                    dataFormatter.formatCellValue(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)),
                    dataFormatter.formatCellValue(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)),
                    dataFormatter.formatCellValue(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK))
            );
            modelArrayList.add(model);
        }

        workbook.close();
        return modelArrayList;
    }
}
