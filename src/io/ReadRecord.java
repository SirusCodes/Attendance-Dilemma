package io;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import models.StudentRawModel;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadRecord {
    public ArrayList<StudentRawModel> readFile(String fileAddress) {
        ArrayList<StudentRawModel> studentRawModelArrayList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileAddress);

            CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();

            CSVReader csv = new CSVReaderBuilder(fileReader)
                    .withSkipLines(2)
                    .withCSVParser(parser)
                    .build();

            List<String[]> data = csv.readAll();

            Locale.setDefault(Locale.US);
            for (String[] row : data) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy, hh:mm:ss a", Locale.US);
                String dateStr = row[2].trim();
                System.out.println("|"+dateStr);
                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                StudentRawModel model = new StudentRawModel(row[0], row[1], date);
                studentRawModelArrayList.add(model);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return studentRawModelArrayList;
    }
}
