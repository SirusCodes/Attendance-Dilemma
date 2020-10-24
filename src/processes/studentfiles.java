package processes;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;

public class studentfiles {
    public static final String path  = "E:\\Studentsfiles.xlsx";

    public static void main(String[] args) throws IOException {


        Workbook workbook = WorkbookFactory.create(new File(path));


        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }

        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }

        workbook.close();
    }
}

