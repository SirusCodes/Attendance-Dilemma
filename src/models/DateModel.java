package models;

import java.io.Reader;

public class DateModel {
    private int studentId;
    private String date;
    private String attendance;

    public DateModel(int studentId, String date, String attendance) {
        this.studentId = studentId;
        this.date = date;
        this.attendance = attendance;
    }

    public DateModel() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "studentId=" + studentId +
                ", date='" + date + '\'' +
                ", attendance=" + attendance +
                '}';
    }
}
