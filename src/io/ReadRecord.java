package io;

import models.StudentRawModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ReadRecord {
    public ArrayList<StudentRawModel> readFile(String fileAddress) {
        ArrayList<StudentRawModel> studentRawModelArrayList = new ArrayList<>();
        String line;
        Locale.setDefault(Locale.ENGLISH);

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress));
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\"");
                String dateStr = split[1];
                String[] student = split[0].split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy, hh:mm:ss a");
                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                StudentRawModel model = new StudentRawModel(student[0], student[1], date);
                studentRawModelArrayList.add(model);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentRawModelArrayList;
    }
}
