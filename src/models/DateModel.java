package models;

import java.sql.Date;

public class DateModel {
    private Date date;
    private String attendance, studentId;

    public DateModel(String studentId, Date date, String attendance) {
        this.studentId = studentId;
        this.date = date;
        this.attendance = attendance;
    }

    public DateModel() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
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
