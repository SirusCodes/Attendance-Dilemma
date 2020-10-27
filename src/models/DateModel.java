package models;

import java.io.Reader;
import java.util.Date;

public class DateModel {
    private int studentId;
    private Date date;
    private String attendance;

    public DateModel(int studentId, Date date, String attendance) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
